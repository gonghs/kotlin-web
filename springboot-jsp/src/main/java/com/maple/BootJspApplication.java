package com.maple;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * 启动类
 *
 * @author maple
 */
@SpringBootApplication
@Configuration
public class BootJspApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BootJspApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootJspApplication.class);
    }

      @Bean
      public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
          return new TomcatServletWebServerFactory() {
              @Override
              protected void postProcessContext(Context context) {
                  super.postProcessContext(context);
                  JspPropertyGroup jspPropertyGroup = new JspPropertyGroup();
                  jspPropertyGroup.setElIgnored("false");
                  jspPropertyGroup.addUrlPattern("*.jsp");
                  jspPropertyGroup.setPageEncoding("UTF-8");
                  jspPropertyGroup.addIncludePrelude("/WEB-INF/tags/taglibs.jspf");
                  JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor =
                          new JspPropertyGroupDescriptorImpl(jspPropertyGroup);
                  context.setJspConfigDescriptor(new JspConfigDescriptorImpl(Collections.singletonList
                  (jspPropertyGroupDescriptor),
                          Collections.emptyList()));
              }
          };
      }
}
