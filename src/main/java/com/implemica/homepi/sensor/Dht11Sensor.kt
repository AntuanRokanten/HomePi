package com.implemica.homepi.sensor

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author ant
 */
class Dht11Sensor(override val pin: Int, private val script: Path) : TemperatureAndHumiditySensor {

    /**
     * Identified of the sensor to used in script
     */
    private val sensorType: Int = 11

    override fun temperature(): Temperature {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun humidity(): Humidity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun temperatureAndHumidity(): TemperatureAndHumidity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun read() {
        val get = Paths.get("")

        println("Reading temperature and humidity....")
        val process = Runtime.getRuntime().exec("python /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py $sensorType $pin")
        process.waitFor()

        println("Data is read")

        val stdInput = BufferedReader(InputStreamReader(process.getInputStream()))
        val stdError = BufferedReader(InputStreamReader(process.getErrorStream()))

        println("Here is the standard output of the command:\n")
        stdInput.lines().forEach { println(it) }

        println("Here is the standard error of the command (if any):\n")
        stdError.lines().forEach { println(it) }
    }

}