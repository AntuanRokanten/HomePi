package com.implemica.homepi

/**
 * @author ant
 */
data class Greeting constructor(val content: String) {
    constructor() : this("default greeting")
}