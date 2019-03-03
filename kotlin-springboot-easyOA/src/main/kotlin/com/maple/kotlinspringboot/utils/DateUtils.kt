package com.maple.kotlinspringboot.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间工具类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 17:34
 */
object DateUtils {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun format2Str(date: Date):String {
        return simpleDateFormat.format(date)
    }
}