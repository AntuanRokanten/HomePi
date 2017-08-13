package com.implemica.homepi.sensor.data

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
        return "Temp (C) = $celcius"
    }
}