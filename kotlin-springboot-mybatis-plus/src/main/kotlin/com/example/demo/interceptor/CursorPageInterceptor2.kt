package com.example.demo.interceptor

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler
import com.example.demo.model.CursorPage
import com.example.demo.model.CursorPageQuery
import org.apache.ibatis.binding.BindingException
import org.apache.ibatis.executor.Executor
import org.apache.ibatis.executor.resultset.ResultSetHandler
import org.apache.ibatis.logging.LogFactory
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.mapping.StatementType
import org.apache.ibatis.plugin.*
import org.apache.ibatis.reflection.SystemMetaObject
import org.apache.ibatis.session.ResultHandler
import org.springframework.validation.BindingResultUtils
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
import java.lang.reflect.Method
import java.sql.Statement
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * 游标分页拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-24 10:59
 */
@Intercepts(
        Signature(type = ResultSetHandler::class, method = "handleResultSets", args = [Statement::class])
)
class CursorPageInterceptor2 : Interceptor, AbstractSqlParserHandler() {
    private val logger = LogFactory.getLog(CursorPageInterceptor2::class.java)

    override fun setProperties(properties: Properties) {
    }

    override fun plugin(target: Any): Any {
        return if (target is ResultSetHandler) {
            Plugin.wrap(target, this)
        } else target
    }

    override fun intercept(invocation: Invocation): Any {
        val resultSetHandler = invocation.target as ResultSetHandler
        val statement = invocation.args[0] as Statement
        val metaObject = SystemMetaObject.forObject(resultSetHandler)
        // 先判断是不是SELECT操作
        val mappedStatement = metaObject.getValue("mappedStatement") as MappedStatement
        if (SqlCommandType.SELECT != mappedStatement.sqlCommandType || StatementType.CALLABLE == mappedStatement.statementType) {
            invocation.proceed()
        }
        val execMethod = getMethod(mappedStatement)
        if (execMethod.returnType.name === CursorPage::class.java.name) {
            val resultList = ArrayList<Any>()
            val resultSet = statement.resultSet
            while (resultSet.next()) {
                val map = HashMap<String, Any>(resultSet.metaData.columnCount)
                for (i in 1..resultSet.metaData.columnCount) {
                    map[resultSet.metaData.getColumnName(i)] = resultSet.getObject(i)
                }
                val obj = JSON.parseObject<Any>(JSON.toJSONString(map), (execMethod.genericReturnType as ParameterizedTypeImpl).actualTypeArguments[0])
                resultList.add(obj)
            }
            return resultList
        }
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
