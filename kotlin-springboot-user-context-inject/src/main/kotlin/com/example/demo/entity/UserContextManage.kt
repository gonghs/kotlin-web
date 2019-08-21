package com.example.demo.entity

/**
 * 用户上下文管理对象 交由spring管理的bean
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-21 14:26
 */
interface UserContextManage {
    fun getUserContext(): UserContext {
        return RequestContext.getUserContext()
    }
}

