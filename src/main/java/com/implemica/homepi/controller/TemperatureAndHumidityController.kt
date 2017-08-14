package com.implemica.homepi.controller

import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import groovy.util.logging.Log4j2
import org.slf4j.Logger
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
@Log4j2
@Controller
class TemperatureAndHumidityController {

    //    private val logger = LoggerFactory.getLogger(javaClass)
    @Autowired
    lateinit var logger: Logger

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
        logger.info("Subscribing to temperature and humidity update. Update rate is $rate minute(s)")
        future = scheduler.scheduleAtFixedRate({ tempAndHum() }, TimeUnit.MINUTES.toMillis(rate))
    }

    @MessageMapping("/temp-and-hum-unsubscribe")
    fun unsubscribe() {
        future.cancel(true) // todo check if already cancelled
    }

}