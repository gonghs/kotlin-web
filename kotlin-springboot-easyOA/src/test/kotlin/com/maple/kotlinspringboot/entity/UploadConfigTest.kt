package com.maple.kotlinspringboot.entity

import com.maple.kotlinspringboot.BaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * 上传配置读取测试
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 21:27
 */
class UploadConfigTest:BaseTest(){
    @Autowired
    lateinit var uploadConfig: UploadConfig

    @Test
    fun testGetConfig(){
        println(uploadConfig.accessKey)
    }
}