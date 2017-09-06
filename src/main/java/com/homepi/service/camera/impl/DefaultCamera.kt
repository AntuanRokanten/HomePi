package com.homepi.service.camera.impl

import com.homepi.service.camera.BytesFromMat
import com.homepi.service.camera.Camera
import com.homepi.service.camera.Frame
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.OpenCVFrameConverter
import org.springframework.stereotype.Component

/**
 * @author ant
 */
@Component
class DefaultCamera constructor(override val deviceId: Int = 0) : Camera {

    /**
     * Grabber for taking frames from the camera stream
     */
    private val grabber: FrameGrabber = FFmpegFrameGrabber("/dev/video$deviceId")

    /**
     * For converting frames to matrices
     */
    private val toMatConverter: OpenCVFrameConverter.ToMat by lazy {
        OpenCVFrameConverter.ToMat()
    }

    @Synchronized override fun takeFrame(): Frame {
        grabber.start()
        val frame = grabber.grabFrame()
        grabber.stop()

        val mat = toMatConverter.convert(frame)
        return Frame(BytesFromMat(mat).bytes(), frame.imageHeight, frame.imageWidth)
    }

}