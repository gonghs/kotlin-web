package com.maple.kotlinspringboot.utils

import com.maple.kotlinspringboot.BaseTest
import com.maple.kotlinspringboot.entity.ResultObj
import com.maple.kotlinspringboot.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * redis 工具测试类
 *
 * @author maple
 * @version 1.0
 * @since 2019-01-20 18:08
 */
class RedisUtilsTest : BaseTest() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val testKey = this.javaClass.name

    @Autowired
    lateinit var redisUtils: RedisUtils

    @Test
    fun testString() {
        log.info("---设置值---")
        redisUtils.setAny(testKey, "hello")
        val str = redisUtils.getT<String>(testKey)
        log.info("---打印值:$str---")
    }

    @Test
    fun testAny() {
        val user = ResultObj.success(User("maple", "man", 18011111111))
        log.info("---设置对象---")
        redisUtils.setAny(testKey, user)
        val testUser = redisUtils.getT<ResultObj<User>>(testKey)
        log.info("---打印值:$testUser---")
        Assert.assertEquals(testUser?.data?.username, user.data?.username)
    }

    @After
    fun clear() {
        redisUtils.delete(testKey)
    }
}