package com.implemica.homepi.gpio.led.impl

import com.implemica.homepi.gpio.led.Led
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState

/**
 * Led implementation for RL50-TUR1TYG136 diode
 *
 * @author ant
 */
class Rl150Led(override val pin: Pin) : Led {

    override val isTurnedOn: Boolean
        get() = outputPin.isHigh

    // todo shutdown gpio controller with setShutdownOptions and unprovision pin
    private val outputPin by lazy { GpioFactory.getInstance().provisionDigitalOutputPin(pin, "$pin LED", PinState.LOW) }

    override fun turnOn() {
        outputPin.high()
    }

    override fun turnOff() {
        outputPin.low()
    }

    override fun blink(toggleDuration: Long, duration: Long) {
        outputPin.blink(toggleDuration, duration)
    }
}

