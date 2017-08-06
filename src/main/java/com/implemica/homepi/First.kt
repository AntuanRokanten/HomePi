package com.implemica.homepi

import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger
import org.springframework.boot.SpringApplication
import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}

/**
 * @author ant
 */
fun main1(args: Array<String>) {
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


    println("=============== start SR-501 ===============")

    System.out.printf("PIR Module Test (CTRL+C to exit)\n")

    // create gpio controller
    val gpio = GpioFactory.getInstance()
    // provision gpio pin #29, (header pin 40) as an input pin with its internal pull down resistor enabled
    val pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29)
    System.out.printf("Ready\n")

    // create a gpio callback trigger on the gpio pin
    val callback = {
        println(" --> GPIO TRIGGER CALLBACK RECEIVED ")
        null
    }
    // create a gpio callback trigger on the PIR device pin for when it's state goes high
    pir.addTrigger(GpioCallbackTrigger(PinState.HIGH, callback))

    // stop all GPIO activity/threads by shutting down the GPIO controller
    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            println("Interrupted, stopping...\n")
            gpio.shutdown()
        }
    })

    // keep program running until user aborts (CTRL-C)
    while (true) {
        Thread.sleep(100)
    }
}