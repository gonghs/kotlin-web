package com.maple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 启动类
 *
 * @author maple
 * @version 1.0
 * @since 2019-03-03 18:51
 */
@SpringBootApplication
open class SpringbootRun : SpringBootServletInitializer()

@RestController
class HelloController {
    @GetMapping
    fun hello(): String {
        return "hello"
    }
}

fun main() {
    runApplication<SpringbootRun>()
}