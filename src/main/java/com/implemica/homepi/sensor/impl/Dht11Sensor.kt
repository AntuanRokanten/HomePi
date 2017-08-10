package com.implemica.homepi.sensor.impl

import com.implemica.homepi.sensor.SensorReadError
import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.Humidity
import com.implemica.homepi.sensor.data.Temperature
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import com.pi4j.io.gpio.Pin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path
import java.util.stream.Collectors

/**
 * DHT11 Temperature and humidity sensor
 *
 * @author ant
 */
class Dht11Sensor(override val pin: Pin, private val script: Path) : TemperatureAndHumiditySensor {

    /**
     * Identified of the sensor to used in script
     */
    private val sensorType: Int = 11

    override fun temperature(): Temperature {
        val result = read()
        return Temperature(parseTemperature(result))
    }

    override fun humidity(): Humidity {
        val result = read()
        return Humidity(parseHumidity(result))
    }

    override fun temperatureAndHumidity(): TemperatureAndHumidity {
        val result = read()
        return TemperatureAndHumidity(Temperature(parseTemperature(result)), Humidity(parseHumidity(result)))
    }

    private fun read(): String {
        println("Reading temperature and humidity....")
        val process = Runtime.getRuntime().exec("python ${script.toAbsolutePath()} $sensorType ${pin.address}")
        process.waitFor()

        println("Data is read")
        println("Checking error stream...")

        val stdError = BufferedReader(InputStreamReader(process.getErrorStream()))
        if (stdError.lines().count() > 0) {
            val error = stdError.lines().collect(Collectors.joining("\n"))
            throw SensorReadError(error)
        }

        val stdInput = BufferedReader(InputStreamReader(process.getInputStream()))

        if (stdInput.lines().count() == 0L) {
            throw SensorReadError("Dht script returned no output. Script path: " + script.toAbsolutePath())
        }

        return stdInput.lines().collect(Collectors.joining("\n"))
    }


    private fun parseTemperature(result: String) = result.substring(result.indexOf("Temp=") + 5, result.indexOf("*")).toDouble()

    private fun parseHumidity(result: String) = result.substring(result.indexOf("Humidity=") + 9, result.indexOf("%")).toDouble()

}