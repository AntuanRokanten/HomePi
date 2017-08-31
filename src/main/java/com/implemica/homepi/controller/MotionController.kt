package com.implemica.homepi.controller

import com.implemica.homepi.gpio.led.InSequenceBlinkMode
import com.implemica.homepi.gpio.led.LedSet
import com.implemica.homepi.gpio.sensor.MotionSensor
import com.implemica.homepi.gpio.sensor.data.MotionEvent
import com.implemica.homepi.service.Camera
import com.implemica.homepi.service.ITelegramBot
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger


/**
 * @author ant
 */
@Controller
class MotionController @Autowired constructor(private val sensor: MotionSensor,
                                              private val template: SimpMessagingTemplate,
                                              private val telegramBot: ITelegramBot,
                                              private val ledSet: LedSet,
                                              private val camera: Camera,
                                              private val logger: Logger) {

    @Volatile private var lastMotionDate: LocalDateTime? = null
    private val telegramEnableReqNum by lazy { AtomicInteger(0) }

    @MessageMapping("/motion-subscribe") // todo inject these values. also to js
    fun subscribe() {
        logger.info("Subscribing to motion events notifications")

        sensor.subscribeToMotionDetection({
            logger.info("Motion is detected")
            lastMotionDate = LocalDateTime.now()
            ledSet.blinkAll(InSequenceBlinkMode(400), 500, 20000)
            template.convertAndSend("/topic/motion", MotionEvent())

            if (telegramEnableReqNum.get() > 0) {
                val picture = camera.takePicture()
                telegramBot.notifyMotionDetected(picture)
            }
        })
    }

    @MessageMapping("/last-motion-date")
    fun lastMotionDate() = lastMotionDate

    @MessageMapping("/enable-telegram-notification")
    fun enableTelegram() {
        telegramEnableReqNum.incrementAndGet()
    }

    @MessageMapping("/disable-telegram-notification")
    fun disableTelegram() {
        telegramEnableReqNum.decrementAndGet()
    }

    @MessageMapping("/motion-unsubscribe")
    fun unsubscribe() {
        logger.info("Unsubscribing from motion events notifications")
        sensor.unsubscribeFromMotionDetection()
    }

}
