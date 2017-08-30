package com.implemica.homepi.service

/**
 * @author ant
 */
interface ComputerVision {

    /**
     * Cuts faces from the picture supplied if any present.
     */
    fun cutFaces(picture: ByteArray) : List<ByteArray>

}