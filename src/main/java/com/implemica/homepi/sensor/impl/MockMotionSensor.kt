package com.implemica.homepi.sensor.impl

import com.implemica.homepi.sensor.MotionSensor
import com.pi4j.io.gpio.Pin
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author ant
 */
class MockMotionSensor(override val pin: Pin) : MotionSensor {

    override var lastMotionDate: LocalDateTime? = null
        get() = field
        set(value) {
            field = value
        }

    override fun isMotionDetected(): Boolean {
        TODO()
    }

    override fun subscribeToMotionDetection(listener: Runnable) {
        while (true) {
            val delay = 15 + (25 - 15) * Random().nextDouble()
            println("----------> Next motion event in ${delay.toLong()} seconds <----------")
            Thread.sleep(TimeUnit.SECONDS.toMillis(delay.toLong()))
            listener.run()
            lastMotionDate = LocalDateTime.now()
        }
    }

    override fun unsubscribeFromMotionDetection() {
        TODO()
    }
}