package com.example.demo.config

import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.InitBinder

/**
 * 集合校验注入
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-29 16:28
 */
    @ControllerAdvice
    class CollectionValidatorAdvice(private val collectionValidator: CollectionValidator) {
        /**
         * 在initBinder阶段修改集合类型的校验器
         */
        @InitBinder
        fun initBinder(binder: WebDataBinder) {
            if (binder.target !is Collection<*>) {
                return
            }
            binder.addValidators(collectionValidator)
        }
    }