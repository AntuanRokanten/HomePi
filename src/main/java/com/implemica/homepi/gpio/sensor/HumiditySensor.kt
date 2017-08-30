package com.implemica.homepi.gpio.sensor

import com.implemica.homepi.gpio.GpioElement
import com.implemica.homepi.gpio.sensor.data.Humidity

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