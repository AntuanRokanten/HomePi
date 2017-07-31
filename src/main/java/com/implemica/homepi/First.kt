package com.implemica.homepi

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author ant
 */
fun main(args: Array<String>) {
    println("Start reading temp...")
    val process = Runtime.getRuntime().exec("python /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py 11 17")
    process.waitFor()

    println("Data is read")

    val stdInput = BufferedReader(InputStreamReader(process.getInputStream()))
    val stdError = BufferedReader(InputStreamReader(process.getErrorStream()))

    println("Here is the standard output of the command:\n")
    stdInput.lines().forEach { println(it) }

    println("Here is the standard error of the command (if any):\n")
    stdError.lines().forEach { println(it) }

}