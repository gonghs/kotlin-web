package com.example.demo.entity

/**
 * 请求上下文
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-21 11:12
 */
object RequestContext {
    /**
     * 用户上下文
     */
    private val userContextThreadLocal: ThreadLocal<UserContext> = ThreadLocal()

    fun setUserContext(userContext: UserContext) {
        userContextThreadLocal.set(userContext)
    }

    fun getUserContext(): UserContext {
        return userContextThreadLocal.get()
    }

    fun removeUserContext() {
        userContextThreadLocal.remove()
    }
}
