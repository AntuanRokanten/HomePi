package com.homepi

import com.homepi.gpio.GpioMock
import com.homepi.gpio.led.LedSet
import com.homepi.gpio.led.impl.Rl50Led
import com.homepi.gpio.sensor.MotionSensor
import com.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.homepi.gpio.sensor.impl.Dht11Sensor
import com.homepi.gpio.sensor.impl.MockMotionSensor
import com.homepi.gpio.sensor.impl.MockTempAndHumSensor
import com.homepi.gpio.sensor.impl.Sr501Sensor
import com.homepi.service.objectdetection.ObjectDetector
import com.homepi.service.objectdetection.impl.FaceDetector
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
import java.net.URI
import java.nio.file.Paths


/**
 * @author ant
 */
@SpringBootApplication
open class App {

    @Bean
    @DefaultProfile
    open fun mockMotionSensor(): MotionSensor = MockMotionSensor(GPIO_29)

    @Bean
    @RpiProfile
    open fun motionSensor(gpio: GpioController): MotionSensor = Sr501Sensor(GPIO_29, gpio)

    @Bean
    @DefaultProfile
    open fun mockTempAndHumSensor(): TemperatureAndHumiditySensor = MockTempAndHumSensor(GPIO_17)

    @Bean
    @RpiProfile
    open fun temperatureAndHumiditySensor(): TemperatureAndHumiditySensor {
        val scriptUri = resourceUri("/python/AdafruitDHT.py")
        return Dht11Sensor(GPIO_17, Paths.get(scriptUri))
    }

    @Bean
    @DefaultProfile
    open fun mokcGpioController(): GpioController = GpioMock()

    @Bean
    @RpiProfile
    open fun gpioController(): GpioController = GpioFactory.getInstance()

    @Bean("tempAndHumScheduler")
    open fun temperatureTaskScheduler(): TaskScheduler = ThreadPoolTaskScheduler()

    @Bean
    open fun faceRecognizer(): ObjectDetector {
        val cascadeUri = resourceUri("/cv/haarcascade_frontalface_alt.xml")
        return FaceDetector(Paths.get(cascadeUri))
    }

    @Bean
    @Scope("prototype")
    open fun logger(ip: InjectionPoint): Logger =
            LoggerFactory.getLogger(ip.member.name) // warning: will not work with field injection

    @Bean
    open fun ledSet(gpio: GpioController): LedSet =
            LedSet(Rl50Led(GPIO_22, gpio), Rl50Led(GPIO_23, gpio), Rl50Led(GPIO_24, gpio), Rl50Led(GPIO_25, gpio))

    private fun resourceUri(path: String): URI {
        return this.javaClass.getResource(path).toURI() ?: throw ExceptionInInitializerError("Unable to find a resource in the following path $path")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}