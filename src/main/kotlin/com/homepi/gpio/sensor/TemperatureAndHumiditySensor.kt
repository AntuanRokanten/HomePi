package com.homepi.gpio.sensor

import com.homepi.gpio.sensor.data.TemperatureAndHumidity

/**
 * Sensor for measuring both humidity and temperature
 *
 * @author ant
 */
interface TemperatureAndHumiditySensor : TemperatureSensor, HumiditySensor {

    /**
     * Returns both humidity and temperature from senor
     */
    fun temperatureAndHumidity(): TemperatureAndHumidity

}