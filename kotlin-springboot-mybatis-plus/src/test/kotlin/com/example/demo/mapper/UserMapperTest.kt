package com.example.demo.mapper

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.demo.DemoApplicationTests
import com.example.demo.model.CursorPageQuery
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * TODO
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-23 17:41
 */
class UserMapperTest : DemoApplicationTests() {
    @Autowired(required = false)
    lateinit var userMapper: UserMapper

    @Test
    fun getPage() {
        val selectPage = userMapper.getUsers(CursorPageQuery("1", 5), "1")
        println(selectPage)
//        selectPage.results.forEach { println(it) }
    }
}