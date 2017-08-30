package com.implemica.homepi.gpio

import com.pi4j.io.gpio.Pin

/**
 * Pi sensor interface
 *
 * @author ant
 */
interface GpioElement {

    /**
     * Pin to which sensor is connected
     * // todo specify the pinout
     */
    val pin: Pin

}