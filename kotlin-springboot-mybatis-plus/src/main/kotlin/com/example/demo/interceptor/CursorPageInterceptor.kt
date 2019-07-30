package com.example.demo.interceptor

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler
import com.example.demo.anno.CursorPageMapper
import com.example.demo.model.CursorPage
import com.example.demo.model.CursorPageQuery
import org.apache.ibatis.binding.BindingException
import org.apache.ibatis.cache.CacheKey
import org.apache.ibatis.executor.Executor
import org.apache.ibatis.logging.LogFactory
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.plugin.*
import org.apache.ibatis.session.ResultHandler
import org.apache.ibatis.session.RowBounds
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.*


/**
 * 游标分页拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-24 10:59
 */
@Intercepts(
        Signature(type = Executor::class, method = "query", args = [MappedStatement::class, Object::class, RowBounds::class, ResultHandler::class]),
        Signature(type = Executor::class, method = "query", args = [MappedStatement::class, Object::class, RowBounds::class, ResultHandler::class, CacheKey::class, BoundSql::class])
)
class CursorPageInterceptor : Interceptor, AbstractSqlParserHandler() {
    private val logger = LogFactory.getLog(CursorPageInterceptor::class.java)
    
    override fun setProperties(properties: Properties) {
    }

    override fun plugin(target: Any): Any {
        return if (target is Executor) {
            Plugin.wrap(target, this)
        } else target
    }

    override fun intercept(invocation: Invocation): Any {
        val executor = invocation.target as Executor
        val args = invocation.args
        val mappedStatement = args[0] as MappedStatement
        val rowBounds = args[2] as RowBounds
        val resultHandler = args[3] as ResultHandler<*>?
        val paramObj = args[1]
        // 判断参数里是否有page对象

        var cursorPageQuery: CursorPageQuery? = null
        if (paramObj is CursorPageQuery) {
            cursorPageQuery = paramObj
        } else if (paramObj is Map<*, *>) {
            for (arg in paramObj.values) {
                if (arg is CursorPageQuery) {
                    cursorPageQuery = arg
                }
            }
        }
        if (null == cursorPageQuery || cursorPageQuery.pageSize < 0)
            return invocation.proceed()
        val boundSql: BoundSql
        boundSql = if (args.size == 4) {
            mappedStatement.getBoundSql(paramObj)
        } else {
            args[5] as BoundSql
        }
        boundSql.setAdditionalParameter("pageCursor", cursorPageQuery.pageCursor)
        boundSql.setAdditionalParameter("pageSize", cursorPageQuery.pageSize + 1)
        val cacheKey = executor.createCacheKey(mappedStatement, paramObj, rowBounds, boundSql)
        val method = getMethod(mappedStatement)
        val execResult = executor.query<Any>(mappedStatement, paramObj, rowBounds, resultHandler, cacheKey, boundSql)
        if (method.returnType.name === List::class.java.name) {
            return execResult
        }
        if (method.returnType.name === CursorPage::class.java.name) {
            val hasNext = execResult.size == cursorPageQuery.pageSize + 1
            // 获取下一个游标的字段名
            val pageCursorFieldName = if (method.isAnnotationPresent(CursorPageMapper::class.java)) {
                method.getAnnotation(CursorPageMapper::class.java).pageCursorField
            } else {
                "id"
            }
            val type = method.genericReturnType as? ParameterizedType
                    ?: throw BindingException("method '" + invocation.method.name +
                            "''s CursorPage must generic return type")
            val pageCursorField = ReflectionUtils.findField(type.actualTypeArguments[0] as Class<*>, pageCursorFieldName)
            pageCursorField ?: throw BindingException("return class can not find pageCursorField")
            val nextCursor = getNextCursor(execResult, pageCursorField, hasNext, cursorPageQuery.pageSize).orEmpty()
            return listOf(CursorPage(cursorPageQuery.pageCursor, cursorPageQuery.pageSize, nextCursor, hasNext, if (hasNext) execResult.subList(0, cursorPageQuery.pageSize) else execResult))
        }
        return execResult
    }

    private fun getNextCursor(resultList: List<Any>, pageCursorField: Field, hasNext: Boolean, pageSize: Int): String? {
        var nextCursor: String? = null
        if (hasNext) {
            val obj = resultList[pageSize]
            try {
                pageCursorField.isAccessible = true
                nextCursor = pageCursorField.get(obj) as? String
            } catch (e: IllegalAccessException) {
                throw BindingException("pageCursorField '" + pageCursorField.name +
                        "' access failure", e)
            } finally {
                pageCursorField.isAccessible = false
            }
        }
        return nextCursor
    }

    private fun getMethod(mappedStatement: MappedStatement): Method {
        val method: Method? = null
        try {
            val id = mappedStatement.id
            val className = id.substring(0, id.lastIndexOf("."))
            val methodName = id.substring(id.lastIndexOf(".") + 1)
            val methods = Class.forName(className).methods
            for (me in methods) {
                if (Objects.equals(me.name, methodName))
                    return me
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        method ?: throw BindingException("method can not find , id:${mappedStatement.id}")
        return method
    }
}
