package com.homepi.gpio.sensor.data

/**
 * Temperature.
 * At the moment only celsius is supported
 *
 * @author ant
 */
class Temperature(private val value: Double) {

    /**
     * Temperature value in celsius
     */
    val celcius: Double
        get() = value

    override fun toString(): String {
        return "Temperature (C) = $celcius"
    }
}