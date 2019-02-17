package com.maple.kotlinspringboot.annotation

/**
 * 注解类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:46
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestAnnotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class CurrentUser
