package com.implemica.homepi.gpio.sensor

import com.implemica.homepi.gpio.GpioElement
import java.time.LocalDateTime

/**
 * Sensor for detecting motion
 *
 * @author ant
 */
interface MotionSensor : GpioElement {

    /**
     * Date of the last motion event detected by the sensor
     */
    var lastMotionDate: LocalDateTime?

    /**
     * Checks if motion is currently being detected
     */
    fun isMotionDetected(): Boolean

    /**
     * Subscribes to motion detection event
     *
     * @param listener listener to be called when motion is detected
     */
    fun subscribeToMotionDetection(listener: ()-> Unit)

    /**
     * Unsubscribes from motion detection event
     */
    fun unsubscribeFromMotionDetection()

}