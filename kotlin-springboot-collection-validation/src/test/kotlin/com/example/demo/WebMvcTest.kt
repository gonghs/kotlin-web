package com.example.demo

import com.example.demo.config.CollectionValidatorConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
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
@Import(CollectionValidatorConfig::class)
class WebMvcTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testDemo() {
        val example = "{\"username\":\"\", \"password\":\"111\"}"
        mockMvc.perform(MockMvcRequestBuilders.post("/demo")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(example))
                .andExpect(status().isOk).andDo { println(it.response.contentAsString) }.andReturn()
    }

    @Test
    fun testDemoList() {
        val example = "[{\"username\":\"\", \"password\":\"111\"}]"
        mockMvc.perform(MockMvcRequestBuilders.post("/demoList")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(example))
                .andExpect(status().isOk).andDo { println(it.response.contentAsString) }.andReturn()
    }

    @Test
    fun testDemoValidList() {
        val example = "{\"list\":[{\"username\":\"\", \"password\":\"111\"}]}"
        mockMvc.perform(MockMvcRequestBuilders.post("/demoValidList")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(example))
                .andExpect(status().isOk).andDo { println(it.response.contentAsString) }.andReturn()
    }

    @Test
    fun testDemoValidList1() {
        val example = "[{\"username\":\"\", \"password\":\"111\"}]"
        mockMvc.perform(MockMvcRequestBuilders.post("/demoValidList1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(example))
                .andExpect(status().isOk).andDo { println(it.response.contentAsString) }.andReturn()
    }

    @Test
    fun testDemoValidList2() {
        val example = "[{\"username\":\"\", \"password\":\"111\"}]"
        mockMvc.perform(MockMvcRequestBuilders.post("/demoValidList2")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(example))
                .andExpect(status().isOk).andDo { println(it.response.contentAsString) }.andReturn()
    }
}