package com.maple.kotlinspringboot.service

import com.maple.kotlinspringboot.entity.UploadResp
import org.springframework.web.multipart.MultipartFile
import java.net.URL


/**
 * 上传服务
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:51
 */
interface IUploadService {
    fun getUrl(keyName: String): URL?
    fun uploadFile(file: MultipartFile): UploadResp
}