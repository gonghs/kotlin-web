package com.maple.kotlinspringboot.utils

import com.maple.kotlinspringboot.BaseTest
import com.maple.kotlinspringboot.entity.SysUser
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 14:22
 */
class RedisTest : BaseTest() {

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val testKey = this.javaClass.name

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, Any>

    @Test
    fun testString() {
        log.info("---设置值---")
        redisTemplate.opsForValue().set(testKey, "hello")
        val str = redisTemplate.opsForValue().get(testKey) as? String
        log.info("---打印值:$str---")
        redisTemplate.delete(testKey)
    }

    @Test
    fun testAny(){

    }
}