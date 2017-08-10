package com.implemica.homepi.sensor

/**
 * @author ant
 */
interface TemperatureAndHumiditySensor : TemperatureSensor, HumiditySensor {

    fun temperatureAndHumidity(): TemperatureAndHumidity

}