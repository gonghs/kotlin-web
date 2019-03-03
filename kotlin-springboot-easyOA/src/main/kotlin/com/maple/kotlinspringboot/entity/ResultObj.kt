package com.maple.kotlinspringboot.entity

/**
 * TODO
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 18:20
 */
data class ResultObj<T>(val data: T?, val success: Boolean) {
    constructor(data: T?) : this(data, true)
}