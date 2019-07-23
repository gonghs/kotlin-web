package com.maple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

/**
 * 启动类
 *
 * @author maple
 * @version 1.0
 * @since 2019-03-03 18:51
 */
@SpringBootApplication
open class SpringbootRun : SpringBootServletInitializer()

class Test {
    @NotBlank(groups = [Check1::class], message = "姓名不允许为空")
    var name: String? = null
    @NotBlank(groups = [Check1::class,Check2::class], message = "密码不允许为空")
    var password: String? = null

    //校验标记接口
    interface Check1
    interface Check2
}

@RestController
class HelloController {
    @GetMapping("/")
    fun hello(test: Test): String {
        return "success"
    }
}

@RestController
class TestController {
    @GetMapping("/test")
    fun test(name:String): String {
        return "name:$name success"
    }
}

fun main() {
    runApplication<SpringbootRun>()
}