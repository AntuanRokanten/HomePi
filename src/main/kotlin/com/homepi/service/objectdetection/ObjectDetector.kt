package com.homepi.service.objectdetection

/**
 * Recognizer fo objects on a picture
 *
 * @author ant
 */
interface ObjectDetector {

    /**
     * Detects objects on the picture provided
     */
    fun detect(frame: ByteArray): Array<ByteArray>

}