package com.homepi.service.objectrecognition

import com.homepi.service.camera.Frame
import java.nio.file.Path

/**
 * Recognizer fo objects on a picture
 *
 * @author ant
 */
interface ObjectRecognizer {

    /**
     * Cascade used for object recognition
     */
    val haarCascadePath: Path

    /**
     * Recognizes objects on the picture provided
     */
    fun recognize(frame: Frame): Array<ByteArray>

}