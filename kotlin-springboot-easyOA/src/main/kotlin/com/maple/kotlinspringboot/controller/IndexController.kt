package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.entity.SysUser
import com.maple.kotlinspringboot.service.IUserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * 登陆控制器
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:49
 */

@Controller
class IndexController {

    @GetMapping("/homePage")
    fun homePage(@CurrentUser sysUser: SysUser,request:HttpServletRequest): String {
        request.setAttribute("username",sysUser.name)
        return "homePage"
    }
}