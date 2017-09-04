package com.homepi.gpio.sensor

import com.homepi.gpio.GpioElement
import com.homepi.gpio.sensor.data.Humidity

/**
 * Sensor for measuring humidity
 *
 * @author ant
 */
interface HumiditySensor : GpioElement {

    /**
     * Returns humidity value from sensor
     */
    fun humidity() : Humidity

}