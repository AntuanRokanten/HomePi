package com.implemica.homepi.controller

import com.implemica.homepi.sensor.MotionSensor
import com.implemica.homepi.sensor.data.MotionEvent
import com.implemica.homepi.service.TelegramBot
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.util.concurrent.Callable


/**
 * @author ant
 */
@Controller
class MotionController @Autowired constructor(private val sensor: MotionSensor,
                                              private val template: SimpMessagingTemplate,
                                              private val telegramBot: TelegramBot,
                                              private val logger: Logger) {

    @Volatile private var notifyTelegram = false

    @MessageMapping("/motion-subscribe") // todo inject these values. also to js
    fun subscribe() {
        logger.info("Subscribing to motion events notifications")

        sensor.subscribeToMotionDetection(Callable<Void> {
            logger.info("Motion is detected")
            template.convertAndSend("/topic/motion", MotionEvent())

            if (notifyTelegram) {
                telegramBot.notifyMotionDetected()
            }

            null
        })
    }

    @MessageMapping("/motion-unsubscribe")
    fun unsubscribe() {
        logger.info("Unsubscribing from motion events notifications")
        sensor.unsubscribeFromMotionDetection()
    }

}
