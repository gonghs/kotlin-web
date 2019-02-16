package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.annotation.TestAnnotation
import com.maple.kotlinspringboot.entity.User
import com.maple.kotlinspringboot.utils.RedisUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-15 15:36
 */

@RestController
class HelloController {
    @Autowired
    lateinit var redisUtils: RedisUtils

    @GetMapping("/hello")
    fun hello(@CurrentUser user:User): String {
        return user.toString()
    }

    @GetMapping("/testLogin")
    fun testLogin(user:User): String {
        redisUtils.setAny("currentUser",user)
        return "success"
    }
}