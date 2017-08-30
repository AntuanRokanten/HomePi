package com.implemica.homepi.service

import com.implemica.homepi.sensor.MotionSensor
import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @author ant
 */
@Component
class TelegramBotImpl @Autowired constructor(
        private val motionSensor: MotionSensor,
        private val tempAndHumSensor: TemperatureAndHumiditySensor,
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
                    "/lastmotion" -> bot.execute(SendMessage(chatId, motionSensor.lastMotionDate.toString())) // todo
                    "/tempindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.temperature().toString()))
                    "/humindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.humidity().toString()))
                    "/tempandhumindoors" -> bot.execute(SendMessage(chatId, tempAndHumSensor.temperatureAndHumidity().toString()))
                    else -> bot.execute(SendMessage(chatId, "\uD83D\uDE45 I am not sure if I can process your command $messageText. Try again \uD83D\uDE01"))
                }
            }
            return@setUpdatesListener UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }

    override fun notifyMotionDetected(picture: ByteArray?) {
        bot.execute(SendPhoto(12, picture))
        chatIds.forEach({ chatId ->
            bot.execute(SendMessage(chatId, "HomePi detected a new motion event"))

            picture?.let {
                bot.execute(SendPhoto(chatId, it))
            }
        })
    }
}