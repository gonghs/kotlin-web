package com.maple.kotlinspringboot.config

import com.maple.kotlinspringboot.entity.ResultObj
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 自定义全局异常处理
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 16:44
 */
@ControllerAdvice
class MyExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ResponseBody
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResultObj<String> {
        log.error(e.message)
        return ResultObj(e.message, false)
    }
}