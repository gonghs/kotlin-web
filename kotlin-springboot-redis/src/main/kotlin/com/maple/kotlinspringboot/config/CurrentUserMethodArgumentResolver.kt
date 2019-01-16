package com.maple.kotlinspringboot.config

import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.entity.User
import org.springframework.context.annotation.Bean
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:49
 */

class CurrentUserMethodArgumentResolver:HandlerMethodArgumentResolver{
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(User::class.java)
                && parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        return User("maple","man",1810000000)
    }
}