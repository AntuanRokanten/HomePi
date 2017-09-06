package com.homepi.service.camera

import java.util.*

/**
 * Represents frame taken from the camera
 *
 * @author ant
 */
class Frame(val bytes: ByteArray, val height: Int, val width: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Frame

        if (!Arrays.equals(bytes, other.bytes)) return false
        if (height != other.height) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(bytes)
        result = 31 * result + height
        result = 31 * result + width
        return result
    }

    override fun toString(): String {
        return "Frame(bytes size=${bytes.size}, height=$height, width=$width)"
    }


}