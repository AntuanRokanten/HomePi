package com.homepi.service.objectdetection.impl

import com.homepi.service.camera.BytesFromMat
import com.homepi.service.objectdetection.ObjectDetector
import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacpp.opencv_core.*
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade
import org.bytedeco.javacpp.opencv_objdetect.cvHaarDetectObjects
import org.bytedeco.javacv.OpenCVFrameConverter
import java.nio.file.Path



/***
 * Recognizes faces on a pictures
 *
 * @author ant
 */
class FaceDetector(override val haarCascadePath: Path) : ObjectDetector {

    private val cascade: CvHaarClassifierCascade by lazy {
        CvHaarClassifierCascade(cvLoad(haarCascadePath.toAbsolutePath().toString()))
    }

    override fun detect(array: ByteArray): Array<ByteArray> {
        println("Start detecting objects")
//        val detectObjects = cvHaarDetectObjects(opencv_core.IplImage(frame), cascade, opencv_core.CvMemStorage.create())
        val frame = Mat() // todo
        val detectObjects = cvHaarDetectObjects(toIplImage(frame), cascade, opencv_core.CvMemStorage.create())
        println("Object detection completed")
        val facesNumber = detectObjects.total()

        return (1..facesNumber).map {
            val rect = opencv_core.CvRect(opencv_core.cvGetSeqElem(detectObjects, it))
            val croppedMat = frame.apply(opencv_core.Rect(rect.x(), rect.y(), rect.width(), rect.height()))

            BytesFromMat(croppedMat).bytes()
        }.toTypedArray()
    }

    fun toIplImage(src: Mat): IplImage {
        val iplConverter = OpenCVFrameConverter.ToIplImage()
        val matConverter = OpenCVFrameConverter.ToMat()
        val frame = matConverter.convert(src)
        return iplConverter.convert(frame)
//        val result = img.clone()
//        img.release()
//        return result
    }

}
