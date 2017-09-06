package com.homepi.service.impl

import com.homepi.service.ObjectRecognizer
import java.nio.file.Path

/***
 * @author ant
 */
//@Component
class FaceRecognizer(override val haarCascade: Path) : ObjectRecognizer {

    override fun recognize(picture: ByteArray): List<ByteArray> {
        TODO("Not yet implemented")
    }

}