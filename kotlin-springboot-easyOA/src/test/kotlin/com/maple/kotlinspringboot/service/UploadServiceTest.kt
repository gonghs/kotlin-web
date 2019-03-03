package com.maple.kotlinspringboot.service

import com.maple.kotlinspringboot.BaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * 上传服务测试
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 22:30
 */
class UploadServiceTest:BaseTest(){
    @Autowired
    lateinit var uploadService: IUploadService

    @Test
    fun testGetUrl(){
        println(uploadService.getUrl("E4F13938985D61C3F67E1E5ADAE4A121"))
        println(uploadService.getUrl("2019-02-24 22:42:44_759.txt"))
    }
}