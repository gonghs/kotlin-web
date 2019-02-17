package com.maple.kotlinspringboot.service

import com.maple.kotlinspringboot.entity.SysUser

/**
 * 用户服务
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:51
 */
interface IUserService {
    fun findByName(name:String): SysUser?
}