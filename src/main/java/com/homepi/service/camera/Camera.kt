package com.homepi.service.camera

import org.bytedeco.javacpp.opencv_core

/**
 * Interface for interacting with camera.
 *
 * @author ant
 */
interface Camera {

    /**
     * Takes picture from the camera
     */
    fun takeFrame(): opencv_core.Mat

}