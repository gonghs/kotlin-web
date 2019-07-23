package com.maple.handler

import org.slf4j.LoggerFactory
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.ConstraintViolationException

/**
 * 全局异常处理拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-03-05 16:16
 */
@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ResponseBody
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String? {
        log.error(e.message)
        if(e is BindException){
            return "${e.fieldError?.field}:${e.fieldError?.defaultMessage}"
        }
        return e.message
    }
}