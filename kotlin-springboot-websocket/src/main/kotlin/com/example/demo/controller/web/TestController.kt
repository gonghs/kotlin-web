package com.example.demo.controller.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * 测试控制器
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-08-08 16:00
 */
@Controller
class TestController {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}
