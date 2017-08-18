package com.implemica.homepi.sensor.impl

import com.implemica.homepi.sensor.MotionSensor
import com.pi4j.io.gpio.Pin
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * @author ant
 */
class MockMotionSensor(override val pin: Pin) : MotionSensor {

    override fun isMotionDetected(): Boolean {
        TODO()
    }

    override fun subscribeToMotionDetection(listener: Callable<Void>) {
        while (true) {
            val delay = 15 + (25 - 15) * Random().nextDouble()
            println("----------> Next motion event in ${delay.toLong()} seconds <----------")
            Thread.sleep(TimeUnit.SECONDS.toMillis(delay.toLong()))
            listener.call()
        }
    }

    override fun unsubscribeFromMotionDetection() {
        TODO()
    }
}