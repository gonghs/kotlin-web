package com.example.demo.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.demo.model.CursorPage
import com.example.demo.model.CursorPageQuery
import com.example.demo.model.UserBO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * 用户操作
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-23 17:33
 */
@Mapper
interface UserMapper : BaseMapper<UserBO> {
    @Select("select id,user_id userId from user where user_id > #{pageCursor} limit #{pageSize}")
    @com.example.demo.anno.CursorPageMapper("userId")
    fun getUsers(page: CursorPageQuery, userId: String): CursorPage<UserBO>
}