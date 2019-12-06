package com.maple.config;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * jsp配置
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-12-05 16:53
 */
@Component
public class JspConfig implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.getJspConfigDescriptor();
    }
}
