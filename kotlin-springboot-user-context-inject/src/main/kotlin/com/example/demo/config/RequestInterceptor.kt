package com.example.demo.config

import com.alibaba.fastjson.JSON
import com.example.demo.entity.RequestContext
import com.example.demo.entity.UserContext
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 请求拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-21 11:19
 */
class RequestInterceptor : HandlerInterceptor {
    /**
     * 如果让请求继续执行则返回true
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userJson = request.getHeader("user-test")
        if (userJson.isNullOrBlank()) {
            return true
        }

        RequestContext.setUserContext(JSON.parseObject(userJson, UserContext::class.java))
        return super.preHandle(request, response, handler)
    }

    /**
     * 请求结束时移除上下文，抛出异常也会执行
     */
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        RequestContext.removeUserContext()
    }
}
