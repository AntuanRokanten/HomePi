package com.homepi.service

/**
 * Interface for interacting with camera.
 *
 * @author ant
 */
interface Camera { // todo add property with id

    /**
     * Takes picture from the camera
     */
    fun takePicture(): ByteArray

}