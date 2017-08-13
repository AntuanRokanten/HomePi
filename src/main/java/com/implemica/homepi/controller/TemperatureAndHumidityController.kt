package com.implemica.homepi.controller

import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Controller
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * Controller for retrieving temperature and humidity data
 *
 * @author ant
 */
@Controller
class TemperatureAndHumidityController {

    /**
     * Injectable temperature and humidity update rate in minutes with default value
     */
    @Value("\${temp.update.rate}")
    private val rate: Long = 1

    @Autowired
    private lateinit var sensor: TemperatureAndHumiditySensor

    @Autowired
    private lateinit var template: SimpMessagingTemplate

    @Autowired
    @Qualifier("tempAndHumScheduler")
    private lateinit var scheduler: TaskScheduler

    private lateinit var future: ScheduledFuture<*> // todo shouldn't it be volatile?

    @MessageMapping("/temp-and-hum")
    @SendTo("/topic/temp-and-hum")
    fun tempAndHum(): TemperatureAndHumidity {
        val temperatureAndHumidity = sensor.temperatureAndHumidity()
        template.convertAndSend("/topic/temp-and-hum", temperatureAndHumidity)
        return temperatureAndHumidity
    }

    @MessageMapping("/temp-and-hum-subscribe")
    fun subscribe() {
        future = scheduler.scheduleAtFixedRate({ tempAndHum() }, TimeUnit.SECONDS.toMillis(rate))
//        future.cancel()

//        val scheduler = Executors.newSingleThreadScheduledExecutor() // todo maybe do it with services
//        scheduler.scheduleAtFixedRate({ tempAndHum() }, 0, 1, TimeUnit.MINUTES)
    }

    @MessageMapping("/temp-and-hum-unsubscribe")
    fun unsubscribe() {
        future.cancel(true) // todo check if already cancelled
    }

}