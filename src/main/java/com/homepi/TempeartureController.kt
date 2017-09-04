package com.homepi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author ant
 */
@RestController()
class TempeartureController {

    @GetMapping("/temperature")
    fun test(): String {

        println("Start reading temp...")
        val process = Runtime.getRuntime().exec("python /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py 11 17")
        process.waitFor()

        println("Data is read")

        val stdInput = BufferedReader(InputStreamReader(process.getInputStream()))
        //    val stdError = BufferedReader(InputStreamReader(process.getErrorStream()))

        var result: String = ""
        stdInput.lines().forEach { result += "\n$it" }

        return result
    }

}