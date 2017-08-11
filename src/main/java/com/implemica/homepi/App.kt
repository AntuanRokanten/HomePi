package com.implemica.homepi

import com.implemica.homepi.sensor.MotionSensor
import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.impl.Dht11Sensor
import com.implemica.homepi.sensor.impl.Sr501Sensor
import com.pi4j.io.gpio.RaspiPin.GPIO_17
import com.pi4j.io.gpio.RaspiPin.GPIO_29
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.nio.file.Paths

/**
 * @author ant
 */
@SpringBootApplication
open class App {

    @Bean
    open fun motionSensor(): MotionSensor {
        return Sr501Sensor(GPIO_29)
    }

    @Bean
    open fun temperatureAndHumiditySensor(): TemperatureAndHumiditySensor {
        return Dht11Sensor(GPIO_17, Paths.get(this.javaClass.getResource("/python/AdfruitDHT.py").toURI()))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}


