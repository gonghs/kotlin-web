package com.example.demo

import com.example.demo.entity.UserContext
import com.example.demo.entity.UserContextManage
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * webMvc测试类
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-30 10:22
 */
@RunWith(SpringRunner::class)
@WebMvcTest(DemoApplication::class)
class WebMvcTest {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testGetArgument() {
        val json = "{\"username\":\"测试\", \"userId\":\"测试\"}"
        mockMvc.perform(MockMvcRequestBuilders.get("/getArgument")
                .header("user-test", json))
                .andExpect(status().isOk).andDo { log.info("返回结果 ${it.response.contentAsString}") }.andReturn()
    }

    @Test
    fun testGetStatic() {
        val json = "{\"username\":\"测试1\", \"userId\":\"测试1\"}"
        mockMvc.perform(MockMvcRequestBuilders.get("/getStatic")
                .header("user-test", json))
                .andExpect(status().isOk).andDo { log.info("返回结果 ${it.response.contentAsString}") }.andReturn()
    }

    @Test
    fun testGetSingletonBean() {
        val json = "{\"username\":\"测试3\", \"userId\":\"测试3\"}"
        mockMvc.perform(MockMvcRequestBuilders.get("/getSingletonBean")
                .header("user-test", json))
                .andExpect(status().isOk).andDo { log.info("返回结果 ${it.response.contentAsString}") }.andReturn()
    }

    @MockBean
    lateinit var userContextManage: UserContextManage

    @Before
    fun before() {
        Mockito.`when`(userContextManage.getUserContext()).thenReturn(UserContext("mock测试", "mock测试"))
    }

    @Test
    fun testMockSingletonBean() {
        log.info(userContextManage.getUserContext().toString())
    }

    @Test
    fun testGetRequestBean() {
        val json = "{\"username\":\"测试4\", \"userId\":\"测试4\"}"
        mockMvc.perform(MockMvcRequestBuilders.get("/getRequestBean")
                .header("user-test", json))
                .andExpect(status().isOk).andDo { log.info("返回结果 ${it.response.contentAsString}") }.andReturn()
    }
}