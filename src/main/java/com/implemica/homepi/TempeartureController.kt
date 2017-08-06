package com.implemica.homepi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author ant
 */
@RestController()
class TempeartureController {

    @GetMapping("/temperature")
    fun test() = temperature()

}