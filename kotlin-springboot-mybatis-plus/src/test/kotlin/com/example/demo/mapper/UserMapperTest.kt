package com.example.demo.mapper

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.demo.DemoApplicationTests
import com.example.demo.model.CursorPageQuery
import com.example.demo.model.UserBO
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

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
        val selectPage = userMapper.getUsers(CursorPageQuery("d744d13189444bd5b436dc80338edf51", 5), "1")
        println("json序列化结果: ${JSON.toJSONString(selectPage)}")
    }
}