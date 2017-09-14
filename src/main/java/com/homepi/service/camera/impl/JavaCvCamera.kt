package com.homepi.service.camera.impl

import com.homepi.service.camera.Camera
import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.OpenCVFrameConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author ant
 */
@Component
class JavaCvCamera @Autowired constructor(private val grabber: FrameGrabber) : Camera {

    /**
     * For converting frames to matrices
     */
    private val toMatConverter: OpenCVFrameConverter.ToMat by lazy {
        OpenCVFrameConverter.ToMat()
    }

    override fun takeFrame(): opencv_core.Mat {
        grabber.start()
        val frame = grabber.grabFrame()
        grabber.stop()

        return toMatConverter.convert(frame)
    }

}