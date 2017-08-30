package com.implemica.homepi.service

/**
 * Interface for interacting with camera.
 *
 * @author ant
 */
interface Camera {

    /**
     * Takes picture from the camera
     */
    fun takePicture(): ByteArray

}