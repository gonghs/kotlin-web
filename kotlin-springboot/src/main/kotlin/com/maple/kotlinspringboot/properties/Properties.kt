package com.maple.kotlinspringboot.properties

import com.maple.kotlinspringboot.config.YamlPropertySourceFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.support.DefaultPropertySourceFactory
import org.springframework.stereotype.Component

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-15 16:12
 */
//
@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource("classpath:/test.yml",factory = YamlPropertySourceFactory::class)
class UserProperties
{
    lateinit var name: String
    lateinit var sex: String
    var phone = 0L
    lateinit var children: Map<String,String>
    lateinit var lists: List<String>

    override fun toString():String{
        return "name:$name,sex:$sex,phone:$phone,children:$children,lists:$lists"
    }
}

//@Configuration
//@PropertySource("classpath:/test.yml", factory = YamlPropertySourceFactory::class)
//data class UserProperties(@Value("\${person.name}") val name: String
//                          , @Value("\${person.sex}") val sex: String
//                          , @Value("\${person.phone}") val phone: Long
//                          , @Value("\${person.children}") val children: Map
//                          )