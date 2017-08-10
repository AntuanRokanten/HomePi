package com.implemica.homepi.sensor

/**
 * @author ant
 */
interface HumiditySensor : Sensor {

    fun humidity() : Humidity

}