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
class LoginController {
    @Autowired
    private lateinit var userService: IUserService

    @RequestMapping("/login",method = [RequestMethod.GET])
    fun login(request:HttpServletRequest): String {
        request.removeAttribute("error")
        return "login"
    }

    @RequestMapping("/login",method = [RequestMethod.POST])
    fun login(account:String,password:String,request:HttpServletRequest): String {
        val subject = SecurityUtils.getSubject()
        val token = UsernamePasswordToken(account, password)
        try {
            subject.login(token)
        }catch (e:Exception){
            token.clear()
            request.setAttribute("error","用户名或密码不正确!")
            return "login"
        }
        return "redirect:index"
    }

    @RequestMapping("/index")
    fun index(@CurrentUser sysUser: SysUser,request:HttpServletRequest): String {
        request.setAttribute("username",sysUser.name)
        return "index"
    }

    @RequestMapping("/logout")
    fun logout(): String {
        // 使用权限管理工具进行用户的退出，注销登录
        SecurityUtils.getSubject().logout()
        return "redirect:login"
    }

    @PostMapping("/error")
    fun error(): String {
        return "error !!"
    }
}