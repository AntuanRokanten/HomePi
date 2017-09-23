package com.homepi.service.camera.impl

import com.homepi.service.camera.Camera
import org.openimaj.image.ImageUtilities
import org.openimaj.video.capture.VideoCapture
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream

/**
 * @author ant
 */
@Component
class OpenImajCamera : Camera {

    @Synchronized
    override fun takeFrame(): ByteArray {
        val capture = VideoCapture(320, 240)
        val stream = ByteArrayOutputStream()
        ImageUtilities.write(capture.iterator().next(), "jpeg", stream)
        capture.stopCapture()
        return stream.toByteArray()
    }

}