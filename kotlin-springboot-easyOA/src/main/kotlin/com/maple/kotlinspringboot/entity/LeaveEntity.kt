package com.maple.kotlinspringboot.entity

import com.maple.kotlinspringboot.enumerate.LeaveStatus
import com.maple.kotlinspringboot.enumerate.LeaveType
import com.maple.kotlinspringboot.utils.DateUtils
import java.util.*

/**
 * 请假管理实体类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 16:15
 */
class LeaveManage {
    var id: Long? = null
    lateinit var userId: String
    var userName: String? = null
    var status: String = "1"
    lateinit var leaveTimeStart: Date
    lateinit var leaveTimeEnd: Date
    var approveNode: Date? = null
    var type: String = "1"
    lateinit var reason: String
    var attach: String? = null
    var leaveTime: String? = null
        get() {
            return "${DateUtils.format2Str(leaveTimeStart)}~${DateUtils.format2Str(leaveTimeEnd)}"
        }
    var statusStr: String? = null
        get() = LeaveStatus.getDescByCode(status).desc
    var typeStr: String? = null
        get() = LeaveType.getDescByCode(type).desc
}