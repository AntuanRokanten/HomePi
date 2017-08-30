package com.implemica.homepi

import com.implemica.homepi.gpio.led.LedSet
import com.implemica.homepi.gpio.led.impl.Rl150Led
import com.implemica.homepi.gpio.sensor.MotionSensor
import com.implemica.homepi.gpio.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.gpio.sensor.impl.MockMotionSensor
import com.implemica.homepi.gpio.sensor.impl.MockTempAndHumSensor
import com.pi4j.io.gpio.RaspiPin.*
import nu.pattern.OpenCV
import org.opencv.objdetect.CascadeClassifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

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
    open fun temperatureTaskScheduler(): TaskScheduler = ThreadPoolTaskScheduler()

    @Bean("faceClassifier")
    open fun faceClassifier() = CascadeClassifier("cv/haarcascade_frontalface_alt.xml")

    @Bean
    @Scope("prototype")
    open fun logger(ip: InjectionPoint): Logger =
            LoggerFactory.getLogger(ip.member.name) // warning: will not work with field injection

    @Bean
    open fun ledSet() = LedSet(Rl150Led(GPIO_00), Rl150Led(GPIO_01), Rl150Led(GPIO_02), Rl150Led(GPIO_03))

}

fun main(args: Array<String>) {
    OpenCV.loadLibrary()
    SpringApplication.run(App::class.java, *args)
}


