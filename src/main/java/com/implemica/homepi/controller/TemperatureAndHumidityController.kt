package com.implemica.homepi.controller

import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Controller for retrieving temperature and humidity data
 *
 * @author ant
 */
@Controller
class TemperatureAndHumidityController {

    @Autowired
    lateinit var sensor: TemperatureAndHumiditySensor

    @MessageMapping("/temp-and-hum")
    @SendTo("/topic/temp-and-hum")
    fun tempAndHum(): TemperatureAndHumidity = sensor.temperatureAndHumidity()

    @MessageMapping("/temp-and-hum-subscribe")
    fun subscribe() {
        val scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler.scheduleAtFixedRate({ tempAndHum() }, 0, 1, TimeUnit.MINUTES)
    }

}