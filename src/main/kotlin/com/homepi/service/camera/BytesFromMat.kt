package com.homepi.service.camera

import org.bytedeco.javacpp.BytePointer
import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacpp.opencv_imgcodecs

/**
 * @author ant
 */
class BytesFromMat constructor(private val mat: opencv_core.Mat) {

    fun bytes(): ByteArray {
        val bytePointer = BytePointer()
        opencv_imgcodecs.imencode(".jpeg", mat, bytePointer)

        return bytePointer.stringBytes
    }

}