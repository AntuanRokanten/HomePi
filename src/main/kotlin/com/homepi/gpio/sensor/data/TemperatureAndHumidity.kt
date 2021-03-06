package com.homepi.gpio.sensor.data

/**
 * @author ant
 */
data class TemperatureAndHumidity constructor(val temperature: Temperature, val humidity: Humidity) {

    override fun toString(): String {
        return "$temperature\n$humidity"
    }

}