package com.example.demo.controller.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 测试控制器
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-08-08 16:00
 */
@Controller
class TestController {
    @GetMapping("/index")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/")
    @ResponseBody
    fun pushData(message: String?): String {
        println("收到消息: $message")
        return "收到消息: $message"
    }
}
