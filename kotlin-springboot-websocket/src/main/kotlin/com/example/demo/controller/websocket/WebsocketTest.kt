package com.example.demo.controller.websocket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

/**
 * websocket 测试控制器
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-08-08 15:37
 */
@ServerEndpoint("/")
@Component
class WebsocketTest {
    private val log = LoggerFactory.getLogger(this.javaClass)
    /**
     * 存储会话信息
     */
    private val session: ThreadLocal<Session> = ThreadLocal.withInitial { null }

    @OnOpen
    fun onOpen(session: Session) {
        this.session.set(session)
        log.info("建立连接")
        sendMessage2Client("建立连接")
    }

    @OnMessage
    fun onMessage(session: Session, message: String) {
        log.info("收到消息: $message")
        sendMessage2Client("收到消息: $message", session)
    }

    @OnClose
    fun onClose() {
        this.session.remove()
        log.info("连接关闭")
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        log.error("发生错误", throwable)
    }

    private fun sendMessage2Client(message: String, session: Session? = this.session.get()) {
        session?.basicRemote?.sendText(message)
    }
}