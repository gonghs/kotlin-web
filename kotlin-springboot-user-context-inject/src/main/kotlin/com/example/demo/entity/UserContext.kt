package com.example.demo.entity

/**
 * 用户上下文
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-20 20:47
 */
open class UserContext(override val userId: String, override val username: String) : IUserContext

//data class UserContext(val userId: String, val username: String)
interface IUserContext {
    val userId: String
    val username: String
}