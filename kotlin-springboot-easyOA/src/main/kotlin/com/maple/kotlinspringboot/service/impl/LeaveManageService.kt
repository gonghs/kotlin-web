package com.maple.kotlinspringboot.service.impl

import com.maple.kotlinspringboot.entity.LeaveManage
import com.maple.kotlinspringboot.entity.SysUser
import com.maple.kotlinspringboot.mapper.ILeaveManageMapper
import com.maple.kotlinspringboot.mapper.IUserMapper
import com.maple.kotlinspringboot.service.ILeaveManageService
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
class LeaveManageService : ILeaveManageService {
    @Autowired(required = false)
    private lateinit var leaveManageMapper: ILeaveManageMapper

    override fun queryById(id: Long): LeaveManage {
        return leaveManageMapper.queryById(id)
    }

    override fun queryLeaveManageList(): List<LeaveManage>? {
        return leaveManageMapper.queryLeaveManageList()
    }

    override fun updateStatus(id: Long, status: String): Int {
        return leaveManageMapper.updateStatus(id, status)
    }

    override fun insert(leaveManage: LeaveManage): Int {
        return leaveManageMapper.insert(leaveManage)
    }

    override fun update(leaveManage: LeaveManage): Int {
        return leaveManageMapper.update(leaveManage)
    }
}