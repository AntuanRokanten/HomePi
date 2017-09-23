package com.homepi.gpio.sensor.impl

import com.homepi.gpio.sensor.MotionSensor
import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioPinDigitalInput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger
import java.time.LocalDateTime
import java.util.concurrent.Callable
import javax.annotation.PreDestroy

/**
 * HC-SR501 motion sensor.
 *
 * @author ant
 */
class Sr501Sensor(override val pin: Pin, private val gpio: GpioController) : MotionSensor {

    @Volatile private var subscribed = false

    override var lastMotionDate: LocalDateTime? = null

    private val pinInput: GpioPinDigitalInput by lazy {
        val inputPin = gpio.provisionDigitalInputPin(pin, "${pin.address} ${this.javaClass.simpleName}")
        inputPin.setShutdownOptions(true, PinState.LOW)
        inputPin
    }

    override fun unsubscribeFromMotionDetection() {
        pinInput.removeAllTriggers()
        subscribed = false
    }

    override fun isMotionDetected() = pinInput.isHigh

    @Synchronized
    override fun subscribeToMotionDetection(listener: () -> Unit) {
        if (!subscribed) {
            pinInput.addTrigger(GpioCallbackTrigger(PinState.HIGH, MotionListener(this, listener)))
            subscribed = true
        }
    }

    @PreDestroy
    fun preDestroy() {
        gpio.unprovisionPin(pinInput)
    }

}

/**
 * Listener which is being invoked when the motion is detected.
 */
private class MotionListener constructor(val motionSensor: MotionSensor, val listener: () -> Unit) : Callable<Void?> {

    override fun call(): Void? {
        listener.invoke()
        motionSensor.lastMotionDate = LocalDateTime.now()
        return null
    }

}