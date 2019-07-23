package com.maple.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.cors.CorsConfiguration



/**
 * 跨域配置
 *
 * @author maple
 * @version 1.0
 * @since 2019-03-12 21:05
 */
@Configuration
open class CorsConfig{

    private fun buildConfig(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        // 允许请求来源
        corsConfiguration.addAllowedOrigin("*")
        // 允许所有请求头
        corsConfiguration.addAllowedHeader("*")
        // 允许所有请求方法
        corsConfiguration.addAllowedMethod("*")
        return corsConfiguration
    }

    @Bean
    open fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", buildConfig()) // 4
        return CorsFilter(source)
    }
}