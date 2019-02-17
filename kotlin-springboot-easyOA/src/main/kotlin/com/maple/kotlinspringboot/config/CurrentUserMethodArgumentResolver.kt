package com.maple.kotlinspringboot.config

import com.maple.kotlinspringboot.annotation.Const
import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.entity.SysUser
import com.maple.kotlinspringboot.utils.RedisUtils
import org.apache.shiro.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * 方法解析器
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:49
 */
@Component
class CurrentUserMethodArgumentResolver:HandlerMethodArgumentResolver{
//    @Autowired
//    lateinit var redisUtils: RedisUtils

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(SysUser::class.java)
                && parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    //从session中取得用户对象注入
    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        return SecurityUtils.getSubject().session.getAttribute(Const.SESSION_USER_KEY)
    }
}