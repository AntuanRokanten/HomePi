package com.implemica.homepi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication



/**
 * @author ant
 */
@SpringBootApplication
open class App {

}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}


