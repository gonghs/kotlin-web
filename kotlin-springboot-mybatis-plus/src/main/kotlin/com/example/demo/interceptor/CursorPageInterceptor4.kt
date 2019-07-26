package com.example.demo.interceptor

import org.apache.ibatis.binding.BindingException
import org.apache.ibatis.executor.parameter.ParameterHandler
import org.apache.ibatis.logging.LogFactory
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.plugin.*
import java.lang.reflect.Method
import java.sql.PreparedStatement
import java.util.*


/**
 * 游标分页拦截器
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-24 10:59
 */
@Intercepts(
        Signature(type = ParameterHandler::class, method = "getParameterObject", args = [])
)
class CursorPageInterceptor4 : Interceptor {
    private val logger = LogFactory.getLog(CursorPageInterceptor4::class.java)

    override fun setProperties(properties: Properties) {
    }

    override fun plugin(target: Any): Any {
        return if (target is ParameterHandler) {
            Plugin.wrap(target, this)
        } else target
    }

    override fun intercept(invocation: Invocation): Any? {
        val parameterHandler = invocation.target as ParameterHandler
        val paramObj = parameterHandler.parameterObject
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
