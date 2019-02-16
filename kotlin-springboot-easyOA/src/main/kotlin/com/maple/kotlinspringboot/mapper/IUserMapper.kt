package com.maple.kotlinspringboot.mapper

import com.maple.kotlinspringboot.entity.DbUser
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * 用户表映射类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-08 23:19
 */
@Mapper
interface IUserMapper {
    @Select("select id,user_id userId,username,password,test from user")
    fun getUser(): List<DbUser>
}