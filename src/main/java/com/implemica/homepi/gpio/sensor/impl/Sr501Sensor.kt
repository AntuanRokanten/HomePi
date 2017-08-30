package com.implemica.homepi.gpio.sensor.impl

import com.implemica.homepi.gpio.sensor.MotionSensor
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalInput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger
import java.time.LocalDateTime
import java.util.concurrent.Callable

/**
 * @author ant
 */
class Sr501Sensor(override val pin: Pin) : MotionSensor {

    @Volatile private var subscribed = false

    override var lastMotionDate: LocalDateTime? = null
        get() = field
        set(value) {
            field = value
        }

    private val pinInput: GpioPinDigitalInput by lazy {
        GpioFactory.getInstance().provisionDigitalInputPin(pin) // todo inject gpio controller
    }

    override fun unsubscribeFromMotionDetection() {
        pinInput.removeAllTriggers()
        subscribed = false
    }

    override fun isMotionDetected() = pinInput.isHigh

    @Synchronized
    override fun subscribeToMotionDetection(listener: Runnable) {
        if (!subscribed) {
            pinInput.addTrigger(GpioCallbackTrigger(PinState.HIGH, MotionListener(this, listener)))
            subscribed = true
        }
    }

}

/**
 * Listener which is being invoked when the motion is detected.
 */
private class MotionListener constructor(val motionSensor: MotionSensor, val listener: Runnable) : Callable<Void?> {

    override fun call(): Void? {
        listener.run()
        motionSensor.lastMotionDate = LocalDateTime.now()
        return null
    }

}