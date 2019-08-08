package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.server.standard.ServerEndpointExporter


/**
 * websocket 配置类
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-08-08 15:34
 */
@Configuration
class WebsocketConfig {
    @Bean
    fun serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }
}
