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

data class CursorPage<T>(val pageCursor: String, val pageSize: Int, val nextCursor: String, val hasNext: Boolean, override val results: List<T>) : Serializable, PageList<T>(pageSize, results) {
    fun <S> map(converter: Function<in T, out S>): CursorPage<S> {
        val converterList = results.map { converter.apply(it) }
        return CursorPage(pageCursor, pageSize, nextCursor, hasNext, converterList)
    }
}

open class PageList<T>(override val size: Int, open val results: List<T>) : List<T> {

    override fun contains(element: T): Boolean {
        return results.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return results.containsAll(elements)
    }

    override fun get(index: Int): T {
        return results[index]
    }

    override fun indexOf(element: T): Int {
        return results.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return results.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return results.iterator()
    }

    override fun lastIndexOf(element: T): Int {
        return results.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<T> {
        return results.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<T> {
        return results.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        return results.subList(fromIndex, toIndex)
    }


}


 