package com.implemica.homepi

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author ant
 */
@Controller
class TestController {

    @Value("\${welcome.message:test}")
    val message = "Hello Default"

    @GetMapping("/welcome")
    fun testView(model : Model) : String {
        model.addAttribute("message", message)
        return "welcome"
    }

}