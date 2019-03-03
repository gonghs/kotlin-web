package com.maple.kotlinspringboot.enumerate

/**
 * 请假管理相关枚举类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 15:58
 */
enum class LeaveStatus(val code: String, val desc: String) {
    UN_SUBMIT("1", "未提交"),
    UN_APPROVING("2", "待审批"),
    APPROVING("3", "审批中"),
    SUCCESS("4", "审批通过"),
    FAILED("5", "审批不通过");

    companion object {
        fun getDescByCode(code: String): LeaveStatus {
            return LeaveStatus.values().filter { it.code == code }[0]
        }
    }
}

enum class LeaveType(val code: String, val desc: String) {
    CASUAL("1", "事假"),
    SICK("2", "病假"),
    ANNUAL("3", "年假");

    companion object {
        fun getDescByCode(code: String): LeaveType {
            return LeaveType.values().filter { it.code == code }[0]
        }
    }
}