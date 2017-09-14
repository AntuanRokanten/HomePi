package com.homepi.service.objectdetection

import org.bytedeco.javacpp.opencv_core
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
    fun detect(frame: opencv_core.Mat): Array<ByteArray>

}