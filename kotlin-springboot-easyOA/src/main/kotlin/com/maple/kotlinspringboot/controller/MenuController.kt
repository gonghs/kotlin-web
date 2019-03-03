package com.maple.kotlinspringboot.controller

import com.maple.kotlinspringboot.annotation.CurrentUser
import com.maple.kotlinspringboot.entity.SysMenu
import com.maple.kotlinspringboot.entity.SysUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * 菜单控制器
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 01:35
 */
@RestController
class MenuController {

    @GetMapping("queryMenuList")
    fun queryMenuList(@CurrentUser sysUser: SysUser): List<SysMenu> {
        return when (sysUser.account) {
            "admin" -> Arrays.asList(SysMenu(1, "请假管理", "/leaveManageForAdmin"))
            "maple" -> Arrays.asList(SysMenu(2, "请假管理", "/leaveManageForUser"))
            else -> Arrays.asList(SysMenu(1, "请假管理", "/leaveManageForAdmin"))
        }
    }
}