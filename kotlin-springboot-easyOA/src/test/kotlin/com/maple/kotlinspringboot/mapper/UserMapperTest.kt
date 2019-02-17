package com.maple.kotlinspringboot.mapper

import com.maple.kotlinspringboot.BaseTest
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * 数据库读取 用户测试类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-08 23:27
 */
class UserMapperTest : BaseTest() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Autowired(required = false)
    lateinit var userMapper: IUserMapper


    @Test
    fun testFindByName() {
        println(userMapper.findByName("admin"))
    }
}