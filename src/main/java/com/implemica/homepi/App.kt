package com.implemica.homepi

import com.implemica.homepi.sensor.MotionSensor
import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.impl.MockMotionSensor
import com.implemica.homepi.sensor.impl.MockTempAndHumSensor
import com.pi4j.io.gpio.RaspiPin.GPIO_17
import com.pi4j.io.gpio.RaspiPin.GPIO_29
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.telegram.telegrambots.ApiContextInitializer

/**
 * @author ant
 */
@SpringBootApplication
open class App {

    @Bean
    open fun motionSensor(): MotionSensor {
//        return Sr501Sensor(GPIO_29)
        return MockMotionSensor(GPIO_29)
    }

    @Bean
    open fun temperatureAndHumiditySensor(): TemperatureAndHumiditySensor {
//        val scriptPath = "/python/AdafruitDHT.py"
//        val script = this.javaClass.getResource(scriptPath) ?: throw ExceptionInInitializerError("Unable to find Python script in $scriptPath")
//        return Dht11Sensor(GPIO_17, Paths.get(script.toURI()))
        return MockTempAndHumSensor(GPIO_17)
    }

    @Bean("tempAndHumScheduler")
    open fun temperatureTaskScheduler(): TaskScheduler {
        return ThreadPoolTaskScheduler()
    }

    @Bean
    @Scope("prototype")
    open fun logger(ip: InjectionPoint): Logger {
        return LoggerFactory.getLogger(ip.member.name) // warning: will not work with field injection
    }

}

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(App::class.java, *args)
}


