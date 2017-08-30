package com.implemica.homepi.gpio.led.impl

import com.implemica.homepi.gpio.led.Led
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.Pin

/**
 * Led implementation for RL50-TUR1TYG136 diode
 *
 * @author ant
 */
class Rl150Led(override val pin: Pin) : Led {

    override val isTurnedOn: Boolean
        get() = outputPin.isHigh

    private val outputPin by lazy { GpioFactory.getInstance().provisionDigitalOutputPin(pin) }

    override fun turnOn() {
        outputPin.setState(true)
    }

    override fun turnOff() {
        outputPin.setState(false)
    }

    override fun blink(toggleDuration: Long, duration: Long) {
        outputPin.blink(toggleDuration, duration)
    }
}