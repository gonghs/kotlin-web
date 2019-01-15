package com.maple.kotlinspringboot.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-15 15:46
 */

@Configuration
class SpringConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("forward:/hello")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}