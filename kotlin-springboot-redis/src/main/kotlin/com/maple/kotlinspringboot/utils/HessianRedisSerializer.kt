package com.maple.kotlinspringboot.utils

import org.springframework.data.redis.serializer.RedisSerializer

/**
 * 实现redis序列化和反序列化
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-09 20:58
 */
class HessianRedisSerializer<T>(private var clazz: Class<T>) : RedisSerializer<T>{

    override fun serialize(t: T?): ByteArray? {
        return SerializeUtils.hessianSerialize(t)
    }

    override fun deserialize(bt: ByteArray?): T? {
        if(bt == null || bt.isEmpty()) return null
        @Suppress("UNCHECKED_CAST")
        return SerializeUtils.hessianDeserialize(bt) as? T
    }
}