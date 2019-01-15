package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.properties.UserProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
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

    @Value("\${person.name}")
    private lateinit var username:String

    @Autowired
    private lateinit var userProperties:UserProperties


    @GetMapping("/hello")
    fun hello(): String {
        return "${userProperties.name},sex:${userProperties.sex},phone:${userProperties.phone}"
    }
}