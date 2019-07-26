package com.example.demo.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.util.function.Function

/**
 * 实体类
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-23 17:26
 */
@TableName("user")
class UserBO {
    @TableId(type = IdType.AUTO)
    var id: String? = null
    var userId: String? = null
    var username: String? = null
    var password: String? = null
    var test: String? = null
    override fun toString(): String {
        return "UserBO(id=$id, userId=$userId, username=$username, password=$password, test=$test)"
    }
}

data class CursorPageQuery(var pageCursor: String, var pageSize: Int = 10) : Serializable {
    constructor() : this("0", DEFAULT_PAGE_SIZE)

    companion object {
        private const val serialVersionUID = -3336525287010509792L
        /**
         * 默认每页显示的记录数
         */
        private const val DEFAULT_PAGE_SIZE = 20
    }
}

data class CursorPage<T>(val pageCursor: String, val pageSize: Int, val nextCursor: String, val hasNext: Boolean, val results: List<T>) : Serializable {
    fun <S> map(converter: Function<in T, out S>): CursorPage<S> {
        val converterList = results.map { converter.apply(it) }
        return CursorPage(pageCursor, pageSize, nextCursor, hasNext, converterList)
    }
}


 