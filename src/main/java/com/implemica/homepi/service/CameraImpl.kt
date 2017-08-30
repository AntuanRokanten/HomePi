package com.implemica.homepi.service

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.videoio.VideoCapture
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @author ant
 */
@Component
class CameraImpl constructor(private val cameraId: Int = 1) : Camera {

    //    , @Qualifier("faceClassifier") private val frontalFaceClassifier: CascadeClassifier
    lateinit var capture: VideoCapture

    @PostConstruct
    fun init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME) // move it from here
        println("OpenCV is loaded. V: " + Core.VERSION)
        capture = VideoCapture(cameraId) // todo what if there is no camera
    }

    override fun takePicture(): ByteArray {
        val image = Mat()
        capture.retrieve(image)

        val buf = MatOfByte()
        Imgcodecs.imencode(".png", image, buf)

        return buf.toArray()
    }

}