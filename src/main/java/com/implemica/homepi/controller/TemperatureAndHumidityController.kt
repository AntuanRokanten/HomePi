package com.implemica.homepi.controller

import com.implemica.homepi.sensor.TemperatureAndHumiditySensor
import com.implemica.homepi.sensor.data.TemperatureAndHumidity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

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
    fun greeting(): TemperatureAndHumidity = sensor.temperatureAndHumidity()
    // todo update regularly
}