package com.homepi.gpio.sensor.impl

import com.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.homepi.gpio.sensor.data.Humidity
import com.homepi.gpio.sensor.data.Temperature
import com.homepi.gpio.sensor.data.TemperatureAndHumidity
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