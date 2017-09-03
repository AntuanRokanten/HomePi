package com.implemica.homepi.gpio.sensor.impl

import com.implemica.homepi.gpio.sensor.SensorReadError
import com.implemica.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.gpio.sensor.data.Humidity
import com.implemica.homepi.gpio.sensor.data.Temperature
import com.implemica.homepi.gpio.sensor.data.TemperatureAndHumidity
import com.pi4j.io.gpio.Pin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path
import java.util.stream.Collectors

/**
 * DHT11 Temperature and humidity sensor
 *
 * @property pin standard RPI 3B GPIO pinout is used here.
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
        val process = Runtime.getRuntime().exec("sudo python ${script.toAbsolutePath()} $sensorType ${pin.address}")
        process.waitFor()

        val stdError = BufferedReader(InputStreamReader(process.errorStream))
                .lines()
                .collect(Collectors.joining("\n"))

        if (!stdError.isEmpty()) {
            throw SensorReadError(stdError)
        }

        val stdInput = BufferedReader(InputStreamReader(process.inputStream))
                .lines()
                .collect(Collectors.joining("\n"))

        if (stdInput.isEmpty()) {
            throw SensorReadError("Dht script returned no output. Script path: " + script.toAbsolutePath())
        }

        return stdInput
    }


    private fun parseTemperature(result: String) = result.substring(result.indexOf("Temp=") + 5, result.indexOf("*")).toDouble()

    private fun parseHumidity(result: String) = result.substring(result.indexOf("Humidity=") + 9, result.indexOf("%")).toDouble()

}