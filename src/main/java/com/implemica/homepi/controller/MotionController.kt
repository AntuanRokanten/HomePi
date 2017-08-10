package com.implemica.homepi.controller

import com.implemica.homepi.sensor.MotionSensor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.concurrent.Callable

/**
 * @author ant
 */
@Controller
class MotionController {

    @Autowired
    lateinit var sensor: MotionSensor

    @MessageMapping("/motion-subscribe")
    fun subscribe() {
        val callback = {
            println(" --> GPIO TRIGGER CALLBACK RECEIVED ")
            null
        }
        sensor.subscribeToMotionDetection(MotionListener())
    }

    private class MotionListener : Callable<Void> {

        @SendTo("/topic/temp-and-hum")
        override fun call(): Void {
            return null!! // returning smthing to the topic
        }
    }

}
