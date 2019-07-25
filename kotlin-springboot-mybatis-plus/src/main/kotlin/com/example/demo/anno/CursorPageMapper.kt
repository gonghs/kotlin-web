package com.example.demo.anno

/**
 * 分页注解
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-24 17:42
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CursorPageMapper(val pageCursorField:String = "id")
