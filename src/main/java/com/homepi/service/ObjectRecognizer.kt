package com.homepi.service

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
    val haarCascade: Path

    /**
     * Recognizes objects on the picture provided
     */
    fun recognize(picture: ByteArray): List<ByteArray>

}