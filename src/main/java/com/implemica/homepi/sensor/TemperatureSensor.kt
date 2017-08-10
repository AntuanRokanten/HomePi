package com.implemica.homepi.sensor

import com.implemica.homepi.sensor.data.Temperature

/**
 * Sensor for measuring temperature
 *
 * @author ant
 */
interface TemperatureSensor : Sensor {

    /**
     * Returns temperature value form sensor
     */
    fun temperature() : Temperature

}