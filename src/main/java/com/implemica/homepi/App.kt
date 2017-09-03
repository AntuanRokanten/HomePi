package com.implemica.homepi

import com.implemica.homepi.gpio.GpioMock
import com.implemica.homepi.gpio.led.LedSet
import com.implemica.homepi.gpio.led.impl.Rl150Led
import com.implemica.homepi.gpio.sensor.MotionSensor
import com.implemica.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.gpio.sensor.impl.Dht11Sensor
import com.implemica.homepi.gpio.sensor.impl.MockMotionSensor
import com.implemica.homepi.gpio.sensor.impl.MockTempAndHumSensor
import com.implemica.homepi.gpio.sensor.impl.Sr501Sensor
import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.RaspiPin.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.nio.file.Paths


/**
 * @author ant
 */
@SpringBootApplication
open class App {

    @Bean
    @DefaultProfile
    open fun mockMotionSensor(): MotionSensor {
        return MockMotionSensor(GPIO_29)
    }

    @Bean
    @RpiProfile
    open fun motionSensor(gpio: GpioController): MotionSensor {
        return Sr501Sensor(GPIO_29, gpio)
    }

    @Bean
    @DefaultProfile
    open fun mockTempAndHumSensor(): TemperatureAndHumiditySensor {
        return MockTempAndHumSensor(GPIO_17)
    }

    @Bean
    @RpiProfile
    open fun temperatureAndHumiditySensor(): TemperatureAndHumiditySensor {
        val scriptPath = "/python/AdafruitDHT.py"
        val script = this.javaClass.getResource(scriptPath) ?: throw ExceptionInInitializerError("Unable to find Python script in $scriptPath")
        return Dht11Sensor(GPIO_17, Paths.get(script.toURI()))
    }

    @Bean
    @DefaultProfile
    open fun mokcGpioController(): GpioController {
        return GpioMock()
    }

    @Bean
    @RpiProfile
    open fun gpioController(): GpioController {
        return GpioFactory.getInstance()
    }

    @Bean("tempAndHumScheduler")
    open fun temperatureTaskScheduler(): TaskScheduler = ThreadPoolTaskScheduler()

    @Bean
    @Scope("prototype")
    open fun logger(ip: InjectionPoint): Logger =
            LoggerFactory.getLogger(ip.member.name) // warning: will not work with field injection

    @Bean
    open fun ledSet(gpio: GpioController): LedSet {
        return LedSet(Rl150Led(GPIO_22, gpio), Rl150Led(GPIO_23, gpio), Rl150Led(GPIO_24, gpio), Rl150Led(GPIO_25, gpio))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}