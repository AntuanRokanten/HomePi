package com.implemica.homepi

/**
 * @author ant
 */
data class HelloMessage constructor(var name: String) {
    constructor() : this("default name")
}