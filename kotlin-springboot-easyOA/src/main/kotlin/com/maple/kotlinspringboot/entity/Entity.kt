package com.maple.kotlinspringboot.entity


/**
 * 实体类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:48
 */

data class User(val username:String, val sex:String,val phone:Long)

data class DbUser(val id:Long,val userId:String,val username:String,val password:String,val test:String)