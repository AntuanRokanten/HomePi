package com.homepi.service.telegram.impl

import com.homepi.gpio.sensor.MotionSensor
import com.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.homepi.service.camera.Camera
import com.homepi.service.telegram.ITelegramBot
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

/**
 * @author ant
 */
@Component
class HomePiBot @Autowired constructor(
        private val motionSensor: MotionSensor,
        private val tempAndHumSensor: TemperatureAndHumiditySensor,
        private val camera: Camera,
        @Value("\${telegram.bot.token}")
        private val token: String,
        @Value("#{'\${telegram.chat.ids}'.split(',')}")
        private val chatIds: List<String>) : ITelegramBot {

    lateinit var bot: TelegramBot

    @PostConstruct
    fun init() {
        bot = TelegramBotAdapter.build(token)
        bot.setUpdatesListener { messages ->
            messages.forEach {
                val chatId = it.message().chat().id()
                val messageText = it.message().text()

                when (messageText) {
                    "/lastmotion" -> bot.execute(SendMessage(chatId, Optional.ofNullable(motionSensor.lastMotionDate).map { it.toString() }.orElse("No motion events were detected since enabling motion tracking")))
                    "/start" -> bot.execute(SendMessage(chatId, "Sup! \uD83D\uDD96 Here you can get the data registered by HomePi sensors \uD83D\uDE4F"))
                    "/tempindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.temperature().toString()))
                    "/humindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.humidity().toString()))
                    "/takeframe" -> bot.execute(SendPhoto(chatId, camera.takeFrame()))
                    "/tempandhumindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.temperatureAndHumidity().toString()))
                    else -> bot.execute(SendMessage(chatId, "\uD83D\uDE45 I am not sure if I can process your command $messageText. Try again \uD83D\uDE01"))
                }
            }
            return@setUpdatesListener UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }

    override fun notifyMotionDetected(picture: ByteArray, vararg faces: ByteArray) {
        chatIds.forEach({ chatId ->
            var msg = "HomePi detected a new motion event."
            if (!faces.isEmpty()) {
                msg += " Number of detected faces is ${faces.size}."
            }
            bot.execute(SendMessage(chatId, msg))

            picture.let {
                bot.execute(SendPhoto(chatId, picture))

                faces.forEach { face -> bot.execute(SendPhoto(chatId, face)) }
            }
        })
    }
}