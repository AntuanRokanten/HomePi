package com.implemica.homepi.sensor

import java.util.concurrent.Callable

/**
 * Sensor for detecting motion
 *
 * @author ant
 */
interface MotionSensor : Sensor {

    /**
     * Checks if motion is currently being detected
     */
    fun isMotionDetected() : Boolean

    /**
     * Subscribes to motion detection event
     *
     * @param listener listener to be called when motion is detected
     */
    fun subscribeToMotionDetection(listener: Callable<Void>)

    /**
     * Unsubscribes from motion detection event
     */
    fun unsubscribeFromMotionDetection()

}