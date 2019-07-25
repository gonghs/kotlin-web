package com.example.demo.mapper

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.demo.DemoApplicationTests
import com.example.demo.model.CursorPageQuery
import com.example.demo.model.UserBO
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.function.Function

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
        val selectPage2 = userMapper.selectPage(Page<UserBO>(1, 4), Wrappers.emptyWrapper())
        selectPage.forEach { println(it) }
//        selectPage.map(Function {it -> it})
        println("------------------------------------------")
        selectPage2.records.forEach { println(it) }
    }
}