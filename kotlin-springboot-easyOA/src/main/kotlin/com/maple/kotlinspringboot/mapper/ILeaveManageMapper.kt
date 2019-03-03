package com.maple.kotlinspringboot.mapper

import com.maple.kotlinspringboot.entity.LeaveManage
import org.apache.ibatis.annotations.*

/**
 * 请假管理映射类
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 16:21
 */
@Mapper
interface ILeaveManageMapper {
    companion object {
        const val tableName: String = "leave_manage"
        const val tableField: String = "t.id,t.user_id userId,t.status,t.leave_time_start leaveTimeStart,t.leave_time_end leaveTimeEnd,t.approve_node approveNode,t.type,t.reason,t.attach"
    }

    @Select("select $tableField  from $tableName t where t.id = #{id}")
    fun queryById(id: Long): LeaveManage

    @Select("select $tableField,u.name userName from $tableName t left join sys_user u on u.id = t.user_id;")
    fun queryLeaveManageList(): List<LeaveManage>?

    @Update("update $tableName t set t.status = #{status} where t.id = #{id}")
    fun updateStatus(@Param("id") id: Long, @Param("status") status: String): Int

    @Insert("insert into $tableName(user_id,status,leave_time_start,leave_time_end,type,reason,attach) " +
            "values(#{userId},#{status},#{leaveTimeStart},#{leaveTimeEnd},#{type},#{reason},#{attach})")
    fun insert(leaveManage: LeaveManage): Int

    @Update("update $tableName t set status = #{status},leave_time_start = #{leaveTimeStart},leave_time_end = #{leaveTimeEnd}" +
            ",type = #{type},reason = #{reason},attach = #{attach} where t.id = #{id}")
    fun update(leaveManage: LeaveManage): Int
}