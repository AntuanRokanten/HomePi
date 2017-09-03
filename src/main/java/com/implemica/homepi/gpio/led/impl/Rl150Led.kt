package com.implemica.homepi.gpio.led.impl

import com.implemica.homepi.gpio.led.Led
import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import javax.annotation.PreDestroy

/**
 * Led implementation for RL50-TUR1TYG136 diode
 *
 * @author ant
 */
class Rl150Led(override val pin: Pin, private val gpio: GpioController) : Led {

    override val isTurnedOn: Boolean
        get() = outputPin.isHigh

    private val outputPin by lazy {
        val digitalOutput = gpio.provisionDigitalOutputPin(pin, "${pin.address} RL150 LED", PinState.LOW)
        digitalOutput.setShutdownOptions(true, PinState.LOW)
        digitalOutput
    }

    override fun turnOn() {
        outputPin.high()
    }

    override fun turnOff() {
        outputPin.low()
    }

    override fun blink(toggleDuration: Long, duration: Long) {
        outputPin.blink(toggleDuration, duration)
    }

    @PreDestroy
    fun preDestroy() {
        gpio.unprovisionPin(outputPin)
    }
}

