package com.implemica.homepi.service.impl

import com.implemica.homepi.service.ComputerVision
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author ant
 */
@Component
class ComputerVisionImpl : ComputerVision {

    override fun cutFaces(picture: ByteArray): List<ByteArray> {
        return Collections.emptyList()
    }

}