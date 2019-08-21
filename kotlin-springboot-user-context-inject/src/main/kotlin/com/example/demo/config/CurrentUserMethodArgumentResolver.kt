package com.example.demo.config

import com.example.demo.annotation.CurrentUser
import com.example.demo.entity.RequestContext
import com.example.demo.entity.UserContext
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * 用户上下文方法参数解析器
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-20 20:55
 */
class CurrentUserMethodArgumentResolver : HandlerMethodArgumentResolver {
    /**
     * 符合条件才进入此参数解析器
     */
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(UserContext::class.java)
                && parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    /**
     * 参数解析并注入对象
     */
    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        return RequestContext.getUserContext()
    }
}