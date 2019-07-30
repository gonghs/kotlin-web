package com.example.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

/**
 * 集合校验器配置类
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-30 16:55
 */
@Configuration
class CollectionValidatorConfig {
    @Autowired
    lateinit var localValidatorFactoryBean: LocalValidatorFactoryBean

    @Bean
    fun collectionValidator(): CollectionValidator {
        return CollectionValidator(localValidatorFactoryBean)
    }
}
