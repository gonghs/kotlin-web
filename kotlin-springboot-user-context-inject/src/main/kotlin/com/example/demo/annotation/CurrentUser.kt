package com.example.demo.annotation

/**
 * 开启用户注入
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-20 20:57
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class CurrentUser
