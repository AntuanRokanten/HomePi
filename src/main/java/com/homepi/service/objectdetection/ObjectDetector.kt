package com.homepi.service.objectdetection

import com.homepi.service.camera.Frame
import java.nio.file.Path

/**
 * Recognizer fo objects on a picture
 *
 * @author ant
 */
interface ObjectDetector {

    /**
     * Cascade used for object detection
     */
    val haarCascadePath: Path

    /**
     * Detects objects on the picture provided
     */
    fun detect(frame: Frame): Array<ByteArray>

}