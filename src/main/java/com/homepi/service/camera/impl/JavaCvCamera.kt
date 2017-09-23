package com.homepi.service.camera.impl

import com.homepi.service.camera.BytesFromMat
import com.homepi.service.camera.Camera
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.OpenCVFrameConverter
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author ant
 */
@Deprecated("Uses javacv which will be removed from the project")
class JavaCvCamera @Autowired constructor(private val logger: Logger,
                                          private val grabber: FrameGrabber) : Camera {

    @Volatile
    private var grabberStarter = false

    /**
     * For converting frames to matrices
     */
    private val toMatConverter: OpenCVFrameConverter.ToMat by lazy {
        OpenCVFrameConverter.ToMat()
    }

    override fun takeFrame(): ByteArray {
//        if (!grabberStarter) {
//            grabber.start()
//            grabberStarter = true
//        }

        logger.info("Starting grabber")
        grabber.start()
        logger.info("Grabber is started. Grabbing a frame")
        val frame = grabber.grabFrame()
        logger.info("Frame is grabbed. Converting a frame to a mat")
        val mat = toMatConverter.convert(frame)
        logger.info("Frame is converted to a mat. Stopping grabber")
        grabber.stop()
        logger.info("Grabber is stopped")

        return BytesFromMat(mat).bytes()
    }

}