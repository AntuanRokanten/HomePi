package com.implemica.homepi.service.impl

import com.implemica.homepi.service.ObjectRecognizer
import org.springframework.stereotype.Component
import java.nio.file.Path

/***
 * @author ant
 */
@Component
class FaceRecognizer(override val haarCascade: Path) : ObjectRecognizer {

    override fun recognize(picture: ByteArray): List<ByteArray> {
        TODO("Not yet implemented")
    }

}