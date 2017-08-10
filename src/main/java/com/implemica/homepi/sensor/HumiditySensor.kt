package com.implemica.homepi.sensor

import com.implemica.homepi.sensor.data.Humidity

/**
 * Sensor for measuring humidity
 *
 * @author ant
 */
interface HumiditySensor : Sensor {

    /**
     * Returns humidity value from sensor
     */
    fun humidity() : Humidity

}