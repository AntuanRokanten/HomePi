package com.implemica.homepi.sensor.impl

import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.Humidity
import com.implemica.homepi.sensor.data.Temperature
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import com.pi4j.io.gpio.Pin
import java.util.*


/**
 * @author ant
 */
class MockTempAndHumSensor(override val pin: Pin) : TemperatureAndHumiditySensor {

    override fun temperature(): Temperature {
        return Temperature(Random().nextInt((35 - 29 + 1) + 29).toDouble())
    }

    override fun temperatureAndHumidity(): TemperatureAndHumidity {
        val temperatureAndHumidity = TemperatureAndHumidity(temperature(), humidity())
        println("Temp and Hum: " + temperatureAndHumidity)
        return temperatureAndHumidity
    }

    override fun humidity(): Humidity {
        return Humidity(Random().nextInt((80 - 30 + 1) + 30).toDouble())
    }

}