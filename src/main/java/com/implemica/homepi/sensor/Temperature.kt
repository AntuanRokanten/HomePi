package com.implemica.homepi.sensor

/**
 * @author ant
 */
class Temperature(private val temp: Double, private val unit: TempUnit) {

    val celcius: Double
        get() = temp

}