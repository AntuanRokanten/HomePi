package com.homepi.gpio.led

import com.homepi.gpio.GpioElement

/**
 * Lite emitting diode integface
 *
 * @author ant
 */
interface Led : GpioElement {

    /**
     * Denotes if this led currently on or off
     */
    val isTurnedOn: Boolean

    /**
     * Turns on the led.
     */
    fun turnOn()

    /**
     * Turns off the led.
     */
    fun turnOff()

    /**
     * Blinks the led
     *
     * @param toggleDuration duration of on or off state (in millis)
     * @param duration total blinking duration (in millis)
     */
    fun blink(toggleDuration: Long, duration: Long)

}