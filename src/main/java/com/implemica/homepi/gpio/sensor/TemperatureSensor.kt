package com.implemica.homepi.gpio.sensor

import com.implemica.homepi.gpio.GpioElement
import com.implemica.homepi.gpio.sensor.data.Temperature

/**
 * Sensor for measuring temperature
 *
 * @author ant
 */
interface TemperatureSensor : GpioElement {

    /**
     * Returns temperature value form sensor
     */
    fun temperature() : Temperature

}