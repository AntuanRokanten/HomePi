package com.homepi.service.telegram

/**
 * @author ant
 */
interface ITelegramBot {

    /**
     * Sends notification about motion event.
     *
     * @param picture optional picture taken at the moment of the motion event.
     * @param faces faces images detected on the picture, if any.
     */
    fun notifyMotionDetected(picture: ByteArray, vararg faces: ByteArray)
}