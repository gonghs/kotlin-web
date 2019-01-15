package com.maple.kotlinspringboot.properties

import com.maple.kotlinspringboot.config.YamlPropertySourceFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.support.DefaultPropertySourceFactory

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-15 16:12
 */

@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource("classpath:/test.yml",factory = YamlPropertySourceFactory::class)
class UserProperties {
    lateinit var name: String
    lateinit var sex: String
    var phone = 0L
}
