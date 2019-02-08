package com.maple.kotlinspringboot.utils

import com.maple.kotlinspringboot.BaseTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.TimeUnit

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-10 12:39
 */
class RedisLockTest : BaseTest(){
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val testRedisKey = this.javaClass.name
    private val lockKey = "testLockKey"
    @Autowired
    lateinit var redisUtils: RedisUtils
    @Autowired
    lateinit var redisClient: RedissonClient
    @Before
    fun setUp() {
        log.info("----------before-----------")
    }

    @After
    fun tearDown() {
        log.info("----------after-----------")
    }

    @Test
    fun setInt(){
        redisUtils.setAny(testRedisKey,100)
        println(redisUtils.getInt(testRedisKey))
    }

    @Test
    fun testLock(){
        val lock:RLock = redisClient.getLock(lockKey)
        println("---尝试上锁---")
        lock.tryLock(20, TimeUnit.SECONDS)
        println("---进入循环---")
        for (i in 0..10){
            val stock = redisUtils.getInt(testRedisKey)
            Thread.sleep(1000)
            if(stock!! > 0){
                redisUtils.setAny(testRedisKey, stock-1)
                println("stock: $stock-1")
            }
        }
        lock.unlock()
    }
}