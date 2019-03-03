package com.maple.kotlinspringboot.entity

import java.io.Serializable


/**
 * 实体类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-16 09:48
 */
data class SysUser(var id: Long, var name: String,var account:String, var password: String): Serializable {
    constructor(account: String,password: String):this(0,"",account,password)
    var roles: MutableList<SysRole> = mutableListOf()
}

data class SysRole(var id: Long, var name: String) {
    var permissions: MutableList<SysMenu> = mutableListOf()
}

data class SysMenu(var id: Long, var name: String, var url: String): Serializable