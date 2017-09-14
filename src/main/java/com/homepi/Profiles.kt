package com.homepi

import org.springframework.context.annotation.Profile
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * Please use the following VM argument to enable a profile:
 * -Dspring.profiles.active=<PROFILE_NAME>
 */

/**
 * Profile to be used on Raspberry Pi
 */
@Target(FUNCTION, CLASS)
@Retention(RUNTIME)
@Profile("rpi")
annotation class RpiProfile

/**
 * Profile to be set by default.
 * Can be used without Raspberry Pi
 * since id doesn't use any RPI specific classes
 */
@Target(FUNCTION, CLASS)
@Retention(RUNTIME)
@Profile("default")
annotation class DefaultProfile

/**
 * Profile to be enabled on computers running linux
 */
@Target(FUNCTION, CLASS)
@Retention(RUNTIME)
@Profile("linux")
annotation class LinuxProfile

/**
 * Profile to be enabled on computers running windows
 */
@Target(FUNCTION, CLASS)
@Retention(RUNTIME)
@Profile("windows")
annotation class WindowsProfile