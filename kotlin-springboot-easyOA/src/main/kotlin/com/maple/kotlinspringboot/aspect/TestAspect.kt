package com.maple.kotlinspringboot.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * 切面测试
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:20
 */
@Aspect
@Component
class TestAspect {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Pointcut("@annotation(com.maple.kotlinspringboot.annotation.TestAnnotation)")
    fun aspect() {

    }

    @Around("aspect()")
    fun doBefore(joinPoint: ProceedingJoinPoint): Any {
        log.info("around")
        return joinPoint.proceed()
    }
}