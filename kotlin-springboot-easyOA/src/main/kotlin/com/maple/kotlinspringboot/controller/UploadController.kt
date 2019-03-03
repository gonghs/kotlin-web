package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.entity.ResultObj
import com.maple.kotlinspringboot.entity.UploadResp
import com.maple.kotlinspringboot.service.IUploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

/**
 * 文件上传控制器
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 21:52
 */
@RestController
class UploadController {
    @Autowired
    lateinit var uploadService: IUploadService

    @PostMapping("/uploadFile")
    fun uploadFile(file: MultipartFile, request: HttpServletRequest): ResultObj<UploadResp> {
        val uploadResp = uploadService.uploadFile(file)
        return ResultObj(uploadResp)
    }
}