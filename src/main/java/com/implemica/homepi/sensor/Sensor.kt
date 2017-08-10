package com.implemica.homepi.sensor

import com.pi4j.io.gpio.Pin

/**
 * Pi sensor interface
 *
 * @author ant
 */
interface Sensor {

    /**
     * Pin to which sensor is connected
     * // todo specify the pinout
     */
    val pin: Pin

}