package com.implemica.homepi.gpio.led

/**
 * @author ant
 */
class LedSet constructor(vararg val leds: Led) {

    fun blinkAll(mode: LedBlinkMode, toggleDuration: Long, duration: Long) {
        when (mode) {
            is SimultaneousBlinkMode -> {
                leds.forEach { it.blink(toggleDuration, duration) }
            }
            is InSequenceBlinkMode -> {
                leds.forEach {
                    it.blink(toggleDuration, duration)
                    Thread.sleep(mode.delay)
                }
            }
            else -> {
                throw UnsupportedOperationException("Unknown blinking mode: ${mode.javaClass}")
            }

        }
    }

}

interface LedBlinkMode

class SimultaneousBlinkMode : LedBlinkMode
class InSequenceBlinkMode constructor(val delay: Long) : LedBlinkMode