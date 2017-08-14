package com.implemica.homepi.controller

import com.implemica.homepi.sensor.MotionSensor
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.concurrent.Callable



/**
 * @author ant
 */
@Controller
class MotionController @Autowired constructor(val sensor: MotionSensor, val logger: Logger){

    @MessageMapping("/motion-subscribe")
    fun subscribe() {
        sensor.subscribeToMotionDetection(MotionListener())
    }

    @MessageMapping("/motion-unsubscribe")
    fun unsubscribe() {
        sensor.unsubscribeFromMotionDetection()
    }

    private class MotionListener : Callable<Void> {

        @SendTo("/topic/motion")
        override fun call(): Void? {
            return null  // returning something to the topic
        }
    }

}
