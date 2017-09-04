package com.homepi.service.impl

import com.homepi.service.Camera
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.highgui.Highgui
import org.opencv.highgui.VideoCapture
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * @author ant
 */
@Component
class DefaultCamera constructor(private val cameraId: Int = 0) : Camera {

    lateinit var capture: VideoCapture

    @PostConstruct
    fun init() {
        capture = VideoCapture()
        capture.open(cameraId)
    }

    @PreDestroy
    fun release() {
        capture.release()
    }

    override fun takePicture(): ByteArray {
        val image = Mat()

        capture.grab()
        capture.retrieve(image)

        val buf = MatOfByte()
        Highgui.imencode(".jpg", image, buf)

        return buf.toArray()
    }

}