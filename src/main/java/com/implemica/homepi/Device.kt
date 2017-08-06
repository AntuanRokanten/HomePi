package com.implemica.homepi

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author ant
 */

fun temperature() : String  {
    println("Start reading temp...")
    val process = Runtime.getRuntime().exec("python /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py 11 17")
    process.waitFor()

    println("Data is read")

    val stdInput = BufferedReader(InputStreamReader(process.getInputStream()))
//    val stdError = BufferedReader(InputStreamReader(process.getErrorStream()))

    var result :String = ""
    stdInput.lines().forEach { result+= "\n$it" }

    return result
}