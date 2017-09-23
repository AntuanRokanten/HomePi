package com.homepi.service.objectdetection.impl

import com.homepi.service.objectdetection.ObjectDetector
import org.openimaj.image.ImageUtilities
import org.openimaj.image.processing.face.detection.HaarCascadeDetector
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Face detector which uses OpenIMAJ library.
 *
 * @author ant
 */
@Service
class OpenImajFaceDetector : ObjectDetector {

    private val detector by lazy { HaarCascadeDetector() }

    override fun detect(frame: ByteArray): Array<ByteArray> {
        val image = ImageUtilities.readF(ByteArrayInputStream(frame))

        return detector.detectFaces(image).map {
            val stream = ByteArrayOutputStream()
            ImageUtilities.write(it.facePatch, "jpeg", stream)
            stream.toByteArray()
        }.toTypedArray()
    }
}