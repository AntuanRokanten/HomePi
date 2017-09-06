package com.homepi.service.impl

import com.homepi.service.Camera
import org.bytedeco.javacpp.BytePointer
import org.bytedeco.javacpp.opencv_imgcodecs.imencode
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

    @Synchronized override fun takePicture(): ByteArray {
        grabber.start()
        val frame = grabber.grabFrame()
        grabber.stop()

        val mat = toMatConverter.convert(frame)
        val bytePointer = BytePointer()
        imencode(".jpeg", mat, bytePointer)

        return bytePointer.stringBytes
    }

}