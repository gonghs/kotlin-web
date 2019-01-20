package com.maple.kotlinspringboot.utils

import org.springframework.data.redis.serializer.RedisSerializer

/**
 * 实现redis序列化和反序列化
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-09 20:58
 */
class JavaRedisSerializer<T> : RedisSerializer<T>{

    override fun serialize(t: T?): ByteArray? {
        return SerializeUtils.javaSerialize(t)
    }

    override fun deserialize(bt: ByteArray?): T? {
        if(bt == null || bt.isEmpty()) return null
        @Suppress("UNCHECKED_CAST")
        return SerializeUtils.javaSerialize(bt) as? T
    }
}