package com.implemica.homepi.sensor.impl

import com.implemica.homepi.sensor.MotionSensor
import com.implemica.homepi.sensor.data.MotionEvent
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalInput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger
import java.util.concurrent.Callable

/**
 * @author ant
 */
class Sr501Sensor(override val pin: Pin) : MotionSensor {

    lateinit var listener: Callable<MotionEvent>

    private val pinInput: GpioPinDigitalInput by lazy {
        GpioFactory.getInstance().provisionDigitalInputPin(pin)
    }

    override fun unsubscribeFromMotionDetection() {
        pinInput.removeAllTriggers()
    }

    override fun isMotionDetected() = pinInput.isHigh


    override fun subscribeToMotionDetection(listener: Callable<Void>) {
//        this.listener = listener
        pinInput.addTrigger(GpioCallbackTrigger(PinState.HIGH, listener))
    }

//    override fun call()  {
//        listener.call()
//    }

}