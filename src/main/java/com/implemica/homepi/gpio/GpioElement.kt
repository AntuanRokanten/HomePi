package com.implemica.homepi.gpio

import com.pi4j.io.gpio.Pin

/**
 * Pi sensor interface
 *
 * @author ant
 */
interface GpioElement {

    /**
     * Pin to which sensor is connected.
     * Pi4J/WiringPi pinout is used.
     */
    val pin: Pin

}