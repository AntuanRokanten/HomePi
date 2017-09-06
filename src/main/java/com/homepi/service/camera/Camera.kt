package com.homepi.service.camera

/**
 * Interface for interacting with camera.
 *
 * @author ant
 */
interface Camera {

    /**
     * Id of the camera device in the system
     */
    val deviceId: Int

    /**
     * Takes picture from the camera
     */
    fun takeFrame(): Frame

}