package com.example.demo

import com.example.demo.model.ValidList
import com.example.demo.model.ValidList1
import com.example.demo.model.ValidList2
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@SpringBootApplication
@RestController
class DemoApplication {
    @PostMapping("/demo")
    fun demo(@RequestBody @Validated user: User): User {
        return user
    }

    @PostMapping("/demoList")
    fun demoList(@RequestBody @Validated user: List<User>): List<User> {
        return user
    }

    @PostMapping("/demoValidList")
    fun demoValidList(@RequestBody @Validated user: ValidList<User>): List<User>? {
        return user.list
    }

    @PostMapping("/demoValidList1")
    fun demoValidList(@RequestBody @Validated user: ValidList1<User>): List<User>? {
        return user.list
    }

    @PostMapping("/demoValidList2")
    fun demoValidList2(@RequestBody @Validated user: ValidList2<User>): List<User>? {
        return user.list
    }
}

class User {
    @NotBlank(message = "用户名不能为空")
    val username: String? = null
    val password: String? = null
}


fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
