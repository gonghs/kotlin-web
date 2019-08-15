package com.maple.kotlinspringboot.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

/**
 * redis 工具类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-02 10:08
 */

@Component
class RedisUtils {
    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, Any>


    /**
     * 获取指定缓存对象
     *
     * @param key 键
     * @return any 值对象
     */
    fun <T : Any> getT(key: String): T? {
        @Suppress("UNCHECKED_CAST")
        return redisTemplate.opsForValue().get(key) as? T
    }

    /**
     * 获取long值
     * kotlin String类型的数字不可以以Long或Int的形式get出来因此单独处理
     *
     * @param key 键
     * @return any 值对象
     */
    fun getLong(key: String): Long? {
        return when (val value = getAny(key)) {
            is Byte -> value.toLong()
            is Int -> value.toLong()
            is Long -> value
            is Float -> value.toLong()
            is Double -> value.toLong()
            is Char -> value.toLong()
            is String -> value.toLongOrNull()
            else -> null
        }
    }

    /**
     * 获取int值
     *
     * @param key 键
     * @return any 值对象
     */
    fun getInt(key: String): Int? {
        return when (val value = getAny(key)) {
            is Byte -> value.toInt()
            is Int -> value
            is Long -> value.toInt()
            is Float -> value.toInt()
            is Double -> value.toInt()
            is Char -> value.toInt()
            is String -> value.toIntOrNull()
            else -> null
        }
    }

    /**
     * 存入普通缓存对象
     *
     * @param key 键
     * @param value 值对象
     * @return Boolean 是否成功
     */
    fun setAny(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
    }

    /**
     * 删除普通缓存对象
     *
     * @param key 键
     * @return Boolean 是否成功
     */
    fun delete(key: String): Boolean {
        return redisTemplate.delete(key)
    }

    /**
     * 获取普通缓存对象
     *
     * @param key 键
     * @return any 值对象
     */
    private fun getAny(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }
}