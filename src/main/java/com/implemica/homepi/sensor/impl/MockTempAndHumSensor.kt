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
        return Temperature(29 + (35 - 29) * Random().nextDouble())
    }

    override fun temperatureAndHumidity(): TemperatureAndHumidity {
        val temperatureAndHumidity = TemperatureAndHumidity(temperature(), humidity())
        println("Temp and Hum: " + temperatureAndHumidity)
        return temperatureAndHumidity
    }

    override fun humidity(): Humidity {
        return Humidity(30 + (80 - 30) * Random().nextDouble())
    }

}