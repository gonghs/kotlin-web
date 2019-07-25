package com.example.demo.interceptor

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler
import com.example.demo.model.CursorPageQuery
import org.apache.ibatis.binding.BindingException
import org.apache.ibatis.executor.statement.StatementHandler
import org.apache.ibatis.logging.LogFactory
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.mapping.StatementType
import org.apache.ibatis.plugin.*
import org.apache.ibatis.reflection.SystemMetaObject
import java.lang.reflect.Method
import java.sql.Connection
import java.util.*


/**
 * 游标分页拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-24 10:59
 */
@Intercepts(
        Signature(type = StatementHandler::class, method = "prepare", args = [Connection::class, Integer::class])
)
class CursorPageInterceptor3 : Interceptor, AbstractSqlParserHandler() {
    private val logger = LogFactory.getLog(CursorPageInterceptor3::class.java)

    override fun setProperties(properties: Properties) {
    }

    override fun plugin(target: Any): Any {
        return if (target is StatementHandler) {
            Plugin.wrap(target, this)
        } else target
    }

    override fun intercept(invocation: Invocation): Any {
        val statementHandler = invocation.target as StatementHandler
        val metaObject = SystemMetaObject.forObject(statementHandler)
        // 先判断是不是SELECT操作
        val mappedStatement = metaObject.getValue("delegate.mappedStatement") as MappedStatement
        if (SqlCommandType.SELECT != mappedStatement.sqlCommandType || StatementType.CALLABLE == mappedStatement.statementType) {
            invocation.proceed()
        }
        val boundSql = metaObject.getValue("delegate.boundSql") as BoundSql
        val paramObj = boundSql.parameterObject
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

        return invocation.proceed()
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
