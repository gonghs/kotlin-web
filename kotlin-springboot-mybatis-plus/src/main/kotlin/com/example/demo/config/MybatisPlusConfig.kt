package com.example.demo.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import com.example.demo.interceptor.CursorPageInterceptor
import com.example.demo.interceptor.CursorPageInterceptor2
import com.example.demo.interceptor.CursorPageInterceptor3
import com.example.demo.interceptor.CursorPageInterceptor4
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * mybatis plus 配置
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-23 18:13
 */
@Configuration
class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    fun paginationInterceptor(): PaginationInterceptor {
        val page = PaginationInterceptor()
        page.setDialectType("mysql")
        return page
    }

    @Bean
    fun cursorPageInterceptor2(): CursorPageInterceptor2 {
        return CursorPageInterceptor2()
    }
//    @Bean
//    fun cursorPageInterceptor4(): CursorPageInterceptor4 {
//        return CursorPageInterceptor4()
//    }
    @Bean
    fun cursorPageInterceptor(): CursorPageInterceptor {
        return CursorPageInterceptor()
    }
}
 