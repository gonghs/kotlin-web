package com.maple.kotlinspringboot.config

import com.maple.kotlinspringboot.shiro.MyShiroRealm
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 权限配置
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-17 15:24
 */
@Configuration
class ShiroConfig {

    @Autowired
    private lateinit var myShiroReam:MyShiroRealm

    @Bean
    fun securityManager(): SecurityManager {
        val securityManager = DefaultWebSecurityManager()
        securityManager.setRealm(myShiroReam)
        return securityManager
    }

    @Bean
    fun shiroFilterFactoryBean(securityManager: SecurityManager): ShiroFilterFactoryBean {
        val shiroFilterFactoryBean = ShiroFilterFactoryBean()
        shiroFilterFactoryBean.securityManager = securityManager
        val map = HashMap<String, String>(2)
        map["/logout"] = "logout"
        map["/css/**"] = "anon"
        map["/js/**"] = "anon"
        //对所有用户认证
        map["/**"] = "authc"
        shiroFilterFactoryBean.loginUrl = "/login"
        shiroFilterFactoryBean.successUrl = "/index"
        shiroFilterFactoryBean.unauthorizedUrl = "/error"
        shiroFilterFactoryBean.filterChainDefinitionMap = map
        return shiroFilterFactoryBean
    }

    @Bean
    fun authorizationAttributeSourceAdvisor(securityManager: SecurityManager):AuthorizationAttributeSourceAdvisor {
        return AuthorizationAttributeSourceAdvisor().apply { this.securityManager = securityManager }
    }
}