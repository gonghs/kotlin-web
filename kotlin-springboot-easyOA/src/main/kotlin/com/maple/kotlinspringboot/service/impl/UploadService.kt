package com.maple.kotlinspringboot.service.impl

import com.aliyun.oss.ClientConfiguration
import com.aliyun.oss.OSSClient
import com.aliyun.oss.common.auth.DefaultCredentialProvider
import com.aliyun.oss.model.ObjectMetadata
import com.maple.kotlinspringboot.entity.UploadConfig
import com.maple.kotlinspringboot.entity.UploadResp
import com.maple.kotlinspringboot.service.IUploadService
import com.maple.kotlinspringboot.utils.DateUtils
import com.maple.kotlinspringboot.utils.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.net.URL
import java.util.*

/**
 * 上传服务实现
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:53
 */
@Service
class UploadService : IUploadService {
    @Autowired
    lateinit var uploadConfig: UploadConfig

    companion object {
        var ossClient: OSSClient? = null
    }

    private fun createOssClient(): OSSClient {
        synchronized(UploadService::class) {
            if (ossClient == null) {
                val credentialsProvider = DefaultCredentialProvider(uploadConfig.accessKey, uploadConfig.secretKey)
                val config = ClientConfiguration()
                ossClient = OSSClient(uploadConfig.ossEndPoint, credentialsProvider, config)
            }
        }
        return ossClient as OSSClient
    }

    override fun getUrl(keyName: String): URL? {
        val ossClient = createOssClient()
        //设置url过期时间
        val expiration = Date(System.currentTimeMillis() + 3600 * 1000)
        return ossClient.generatePresignedUrl(uploadConfig.bucket, keyName, expiration)
    }

    override fun uploadFile(file: MultipartFile): UploadResp {
        val ossClient = createOssClient()
        val meta = ObjectMetadata()
        meta.contentType = file.contentType
        meta.contentLength = file.size
        val fileExt = FileUtils.getFileExt(file.originalFilename as String)
        val pathWithDir = "${DateUtils.format2Str(Date())}_${Random().nextInt(1000)}.$fileExt"
        val objectResult = ossClient.putObject(uploadConfig.bucket, pathWithDir, ByteArrayInputStream(file.bytes), meta)
        return UploadResp(objectResult.eTag,pathWithDir)
    }
}