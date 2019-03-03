package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.entity.LeaveManage
import com.maple.kotlinspringboot.entity.ResultObj
import com.maple.kotlinspringboot.entity.SysUser
import com.maple.kotlinspringboot.enumerate.LeaveStatus
import com.maple.kotlinspringboot.service.ILeaveManageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

/**
 * TODO
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 01:48
 */
@Controller
class LeaveManageController {

    @Autowired
    lateinit var leaveManageService: ILeaveManageService

    @GetMapping("/leaveManageForUser")
    fun leaveManageForUser(): String {
        return "leaveManage/userPage"
    }

    @GetMapping("/leaveManageForAdmin")
    fun leaveManageForAdmin(): String {
        return "leaveManage/adminPage"
    }

    @PostMapping("/queryLeaveManageList")
    @ResponseBody
    fun queryLeaveManegeList(): List<LeaveManage>? {
        return leaveManageService.queryLeaveManageList()
    }

    /**
     * 提交请假单
     */
    @GetMapping("/submitLeaveApplication")
    @ResponseBody
    fun submitLeaveApplication(id: Long): ResultObj<String> {
        if (leaveManageService.updateStatus(id, LeaveStatus.UN_APPROVING.code) > 0) {
            return ResultObj("成功")
        }
        return ResultObj("提交失败", false)
    }

    /**
     * 撤回请假单
     */
    @GetMapping("/recallLeaveApplication")
    @ResponseBody
    fun recallLeaveApplication(id: Long): ResultObj<String> {
        val leaveManage = leaveManageService.queryById(id)
        //只有待审核允许撤回
        if (leaveManage.status != LeaveStatus.UN_APPROVING.code)
            return ResultObj("撤回失败", false)
        if (leaveManageService.updateStatus(id, LeaveStatus.UN_SUBMIT.code) > 0)
            return ResultObj("成功")
        return ResultObj("撤回失败", false)
    }

    @PostMapping("saveOrUpdateLeaveManage")
    @ResponseBody
    fun saveOrUpdateLeaveManage(@RequestBody leaveManage: LeaveManage,@CurrentUser sysUser: SysUser): ResultObj<String> {
        leaveManage.status = LeaveStatus.UN_SUBMIT.code
        leaveManage.userId = sysUser.id.toString()
        if (leaveManage.id == null) {
            if (leaveManageService.insert(leaveManage) < 1)
                return ResultObj("保存失败", false)
        } else {
            if (leaveManageService.update(leaveManage) < 1)
                return ResultObj("保存失败", false)
        }
        return ResultObj("保存成功")
    }

    @PostMapping("saveOrUpdateAndSubmitLeaveManage")
    @ResponseBody
    fun saveOrUpdateAndSubmitLeaveManage(@RequestBody leaveManage: LeaveManage,@CurrentUser sysUser: SysUser): ResultObj<String> {
        leaveManage.status = LeaveStatus.UN_APPROVING.code
        leaveManage.userId = sysUser.id.toString()
        if (leaveManage.id == null) {
            if (leaveManageService.insert(leaveManage) < 1)
                return ResultObj("保存失败", false)
        } else {
            if (leaveManageService.update(leaveManage) < 1)
                return ResultObj("保存失败", false)
        }
        return ResultObj("保存成功")
    }
}