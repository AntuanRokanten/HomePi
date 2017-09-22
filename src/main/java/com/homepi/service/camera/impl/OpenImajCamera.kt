package com.homepi.service.camera.impl

import com.homepi.service.camera.Camera
import org.openimaj.image.ImageUtilities
import org.openimaj.image.MBFImage
import org.openimaj.video.capture.VideoCapture
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream

/**
 * @author ant
 */
@Component
class OpenImajCamera : Camera {

//    private val videoCapture by lazy {
//        VideoCapture(320, 240) // todo refactor
//    }

    private val grabber : FrameGrabber by lazy {
        FrameGrabber(VideoCapture(320, 240))
    }

    override fun takeFrame(): ByteArray {
        val stream = ByteArrayOutputStream()
        ImageUtilities.write(grabber.currentFrame(), "jpeg", stream)
        return stream.toByteArray()
    }

    // todo predestroy
}

class FrameGrabber(private val capture: VideoCapture) {

    @Volatile private lateinit var currentFrame: MBFImage

    init {
        for (frame in capture) {
            currentFrame = frame
        }
    }

    fun currentFrame(): MBFImage = currentFrame
}