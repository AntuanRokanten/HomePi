package com.homepi.gpio.sensor.data

/**
 * @author ant
 */
data class Humidity constructor(val value: Double) {

    override fun toString(): String {
        return "Humidity (%) = $value"
    }

}