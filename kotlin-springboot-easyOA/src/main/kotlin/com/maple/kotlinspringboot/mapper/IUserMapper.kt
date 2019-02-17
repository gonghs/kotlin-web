package com.maple.kotlinspringboot.mapper

import com.maple.kotlinspringboot.entity.SysUser
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

    @Select("select t.id,t.name,t.account,t.password from sys_user t where t.name = #{name}")
    fun findByName(name: String):SysUser?
}