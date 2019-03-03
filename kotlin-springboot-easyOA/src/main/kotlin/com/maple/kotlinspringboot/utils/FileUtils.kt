package com.maple.kotlinspringboot.utils

/**
 * TODO
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 22:16
 */
object FileUtils{
    /**
     * 获取文件扩展名
     */
    fun getFileExt(fileName: String): String? {
        return if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            fileName.substring(fileName.lastIndexOf(".") + 1)
        } else {
            ""
        }
    }
}