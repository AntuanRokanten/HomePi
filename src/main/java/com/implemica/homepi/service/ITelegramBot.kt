package com.implemica.homepi.service

/**
 * @author ant
 */
interface ITelegramBot {
    fun notifyMotionDetected(picture: ByteArray? = null)
}