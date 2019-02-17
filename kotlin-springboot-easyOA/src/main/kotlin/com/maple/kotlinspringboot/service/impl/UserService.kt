package com.maple.kotlinspringboot.service.impl

import com.maple.kotlinspringboot.entity.SysUser
import com.maple.kotlinspringboot.mapper.IUserMapper
import com.maple.kotlinspringboot.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户服务实现
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:53
 */
@Service
class UserService : IUserService {

    @Autowired(required = false)
    private lateinit var userMapper: IUserMapper

    override fun findByName(name: String): SysUser? {
        return userMapper.findByName(name)
    }

}