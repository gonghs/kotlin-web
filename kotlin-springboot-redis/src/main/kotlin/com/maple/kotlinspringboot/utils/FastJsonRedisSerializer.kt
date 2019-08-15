package com.maple.kotlinspringboot.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.springframework.data.redis.serializer.RedisSerializer
import java.nio.charset.Charset



/**
 * 实现redis序列化和反序列化
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-09 20:58
 */
class FastJsonRedisSerializer<T>(private val clazz:Class<T> ) : RedisSerializer<T>{
    private val charset = Charset.forName("utf-8")

    override fun serialize(t: T?): ByteArray? {
        return JSON.toJSONBytes(t, SerializerFeature.WriteClassName)
    }

    override fun deserialize(bt: ByteArray?): T? {
        if(bt == null || bt.isEmpty()) return null
        val str = String(bt, charset)
        return JSON.parseObject<T>(str, clazz)
    }
}