package com.example.demo

import com.example.demo.annotation.CurrentUser
import com.example.demo.entity.IUserContext
import com.example.demo.entity.RequestContext
import com.example.demo.entity.UserContext
import com.example.demo.entity.UserContextManage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Provider

@SpringBootApplication
@RestController
class DemoApplication {
    @Autowired
    lateinit var userContextManage: UserContextManage
    @Autowired
    lateinit var userContextProvider: Provider<UserContext>
    // 这个测试会丢失字段值
//    @Autowired
//    lateinit var userContext: UserContext
    @Autowired
    lateinit var userContext: IUserContext

    @GetMapping("/getArgument")
    fun getArgument(@CurrentUser userContext: UserContext): UserContext {
        return userContext
    }

    @GetMapping("/getStatic")
    fun getStatic(): UserContext {
//        throw RuntimeException("啊偶 出错了")
        return RequestContext.getUserContext()
    }

    @GetMapping("/getSingletonBean")
    fun getSingletonBean(): UserContext {
        return userContextManage.getUserContext()
    }

    // 这个测试将丢失值
//    @GetMapping("/getRequestBean")
//    fun getRequestBean(): UserContext {
//        return userContext
//    }
    @GetMapping("/getRequestBean")
    fun getRequestBean(): IUserContext {
        return userContext
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
