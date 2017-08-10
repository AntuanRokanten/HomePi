package com.implemica.homepi.sensor

/**
 * Exception denotes error while taking measures from sensor
 *
 * @author ant
 */
class SensorReadError constructor(message: String) : Exception(message) {

    constructor() : this("")

}