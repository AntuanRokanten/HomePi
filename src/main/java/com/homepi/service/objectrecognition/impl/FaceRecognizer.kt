package com.homepi.service.objectrecognition.impl

import com.homepi.service.camera.BytesFromMat
import com.homepi.service.camera.Frame
import com.homepi.service.objectrecognition.ObjectRecognizer
import org.bytedeco.javacpp.BytePointer
import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacpp.opencv_core.cvLoad
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade
import org.bytedeco.javacpp.opencv_objdetect.cvHaarDetectObjects
import java.nio.file.Path

/***
 * Recognizes faces on a pictures
 *
 * @author ant
 */
class FaceRecognizer(override val haarCascadePath: Path) : ObjectRecognizer {

    private val cascade: CvHaarClassifierCascade by lazy {
        CvHaarClassifierCascade(cvLoad(haarCascadePath.toAbsolutePath().toString()))
    }

    override fun recognize(frame: Frame): Array<ByteArray> {
        val img = opencv_core.IplImage.createHeader(
                opencv_core.CvSize(frame.width, frame.height),
                opencv_core.IPL_DEPTH_8U,
                3
        )

        opencv_core.cvSetData(img, BytePointer(*frame.bytes), frame.width * 3)
        val imgMat = opencv_core.cvarrToMat(img)

        val detectObjects = cvHaarDetectObjects(img, cascade, opencv_core.CvMemStorage.create())
        val facesNumber = detectObjects.total()

        return (0..facesNumber).map {
            val rect = opencv_core.CvRect(opencv_core.cvGetSeqElem(detectObjects, it))
            val croppedMat = imgMat.apply(opencv_core.Rect(rect.x(), rect.y(), rect.width(), rect.height()))

            return@map BytesFromMat(croppedMat).bytes()
        }.toTypedArray()
    }

}
