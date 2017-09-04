package com.homepi.gpio.sensor

import com.homepi.gpio.GpioElement
import com.homepi.gpio.sensor.data.Temperature

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