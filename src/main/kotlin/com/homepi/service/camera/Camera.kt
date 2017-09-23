package com.homepi.service.camera

/**
 * Interface for interacting with camera.
 *
 * @author ant
 */
interface Camera {

    /**
     * Takes picture from the camera
     */
    fun takeFrame(): ByteArray

}