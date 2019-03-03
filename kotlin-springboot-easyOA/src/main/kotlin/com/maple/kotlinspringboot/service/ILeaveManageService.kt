package com.maple.kotlinspringboot.service

import com.maple.kotlinspringboot.entity.LeaveManage

/**
 * 用户服务
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:51
 */
interface ILeaveManageService {
    fun queryById(id: Long): LeaveManage

    fun queryLeaveManageList(): List<LeaveManage>?

    fun updateStatus(id: Long, status: String): Int

    fun insert(leaveManage: LeaveManage): Int

    fun update(leaveManage: LeaveManage): Int
}