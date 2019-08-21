package com.example.demo.config

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import com.example.demo.entity.IUserContext
import com.example.demo.entity.RequestContext
import com.example.demo.entity.UserContext
import com.example.demo.entity.UserContextManage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

/**
 * spring配置
 *
 * @author maple
 * @version 1.0
 * @since 2019-08-21 10:53
 */
@Configuration
class SpringConfig : WebMvcConfigurer {

    @Bean
    fun userContextManage(): UserContextManage {
        return object : UserContextManage {}
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun userContext(): UserContext {
        return RequestContext.getUserContext()
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    fun iUserContext(): IUserContext {
        return RequestContext.getUserContext()
    }


    /**
     * 加入解析器列表
     */
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(CurrentUserMethodArgumentResolver())
    }

    /**
     * 加入拦截器列表
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(RequestInterceptor())
    }

    @Bean
    fun httpMessageConverter(): HttpMessageConverter<*> {
        //创建fastJson消息转换器
        val fastConverter = FastJsonHttpMessageConverter()

        //升级最新版本需加=============================================================
        val supportedMediaTypes = ArrayList<MediaType>()
        supportedMediaTypes.add(MediaType.APPLICATION_JSON)
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8)
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML)
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED)
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM)
        supportedMediaTypes.add(MediaType.APPLICATION_PDF)
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML)
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML)
        supportedMediaTypes.add(MediaType.APPLICATION_XML)
        supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA)
        supportedMediaTypes.add(MediaType.IMAGE_GIF)
        supportedMediaTypes.add(MediaType.IMAGE_JPEG)
        supportedMediaTypes.add(MediaType.IMAGE_PNG)
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM)
        supportedMediaTypes.add(MediaType.TEXT_HTML)
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN)
        supportedMediaTypes.add(MediaType.TEXT_PLAIN)
        supportedMediaTypes.add(MediaType.TEXT_XML)
        fastConverter.supportedMediaTypes = supportedMediaTypes

        //创建配置类
        val fastJsonConfig = FastJsonConfig()
        //修改配置返回内容的过滤
        //WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        //WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        //DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        //WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        //WriteMapNullValue：是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue
        )
        fastConverter.fastJsonConfig = fastJsonConfig

        return fastConverter
    }
}
