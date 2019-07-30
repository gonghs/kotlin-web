package com.example.demo.config

import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

/**
 * 集合验证器
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-30 16:48
 */
class CollectionValidator(private val validatorFactory: LocalValidatorFactoryBean) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return Collection::class.java.isAssignableFrom(clazz)
    }

    /**
     * 校验集合 遇到失败即退出
     *
     * @param target 受校验对象
     * @param errors 错误结果
     */
    override fun validate(target: Any, errors: Errors) {
        val collection = target as Collection<*>
        for (`object` in collection) {
            `object`?.let { ValidationUtils.invokeValidator(validatorFactory, `object`, errors) }
            // 存在错误即退出校验
            if (errors.hasErrors()) {
                break
            }
        }
    }
}