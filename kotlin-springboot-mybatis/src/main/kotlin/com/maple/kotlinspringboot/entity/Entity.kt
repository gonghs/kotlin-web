package com.maple.kotlinspringboot.entity

import java.io.Serializable

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:48
 */

data class User(val username:String, val sex:String,val phone:Long)
class TestUser(){
    lateinit var username:String
    lateinit var sex:String
    private var phone = 0L
    constructor(username:String, sex:String, phone:Long):this(){
        this.username = username
        this.sex = sex
        this.phone = phone
    }
    override fun toString():String {
        return "username:$username,sex:$sex,phone:$phone"
    }
}