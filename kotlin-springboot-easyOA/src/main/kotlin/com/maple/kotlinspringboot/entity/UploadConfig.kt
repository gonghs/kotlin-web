package com.maple.kotlinspringboot.entity

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 阿里云上传配置
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 21:23
 */


@Component
@ConfigurationProperties(prefix = "upload.config")
class UploadConfig {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var ossEndPoint: String
    lateinit var bucket: String
    var fullUrl:String? = null
    get() = "http://$bucket.$ossEndPoint/"
}