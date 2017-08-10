package com.implemica.homepi.sensor

/**
 * @author ant
 */
interface TemperatureSensor : Sensor {

    fun temperature() : Temperature

}