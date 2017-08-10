package com.implemica.homepi.sensor

import com.implemica.homepi.sensor.data.TemperatureAndHumidity

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