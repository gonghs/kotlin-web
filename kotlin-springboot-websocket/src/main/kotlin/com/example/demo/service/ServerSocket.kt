package com.example.demo.service

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.Charset

/**
 * serverSocket
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-08-13 23:41
 */
class SocketServer {
    //解码buffer
    private val cs: Charset = charset("GB2312")
    //接受数据缓冲区
    private var sBuffer: ByteBuffer = ByteBuffer.allocate(1024)
    //发送数据缓冲区
    private val rBuffer: ByteBuffer = ByteBuffer.allocate(1024)
    //选择器（叫监听器更准确些吧应该）
    private lateinit var selector: Selector

    /**
     * 启动socket服务，开启监听
     *
     * @param port
     */
    fun startSocketServer(port: Int) {
        try {
            //打开通信信道
            val serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false)
            //获取套接字
            val serverSocket = serverSocketChannel.socket()
            //绑定端口号
            serverSocket.bind(InetSocketAddress(port))
            //打开监听器
            selector = Selector.open()
            //将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)

            //监听器会一直监听，如果客户端有请求就会进入相应的事件处理
            while (true) {
                selector.select()//select方法会一直阻塞直到有相关事件发生或超时
                val selectionKeys = selector.selectedKeys()//监听到的事件
                for (key in selectionKeys) {
                    handle(key)
                }
                //清除处理过的事件
                selectionKeys.clear()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 处理不同的事件
     *
     * @param selectionKey
     */
    private fun handle(selectionKey: SelectionKey) {
        val serverSocketChannel: ServerSocketChannel
        val socketChannel: SocketChannel
        var requestMsg = ""
        var count = 0
        if (selectionKey.isAcceptable) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = selectionKey.channel() as ServerSocketChannel
            socketChannel = serverSocketChannel.accept()
            socketChannel.configureBlocking(false)
            socketChannel.register(selector, SelectionKey.OP_READ)
        } else if (selectionKey.isReadable) {
            socketChannel = selectionKey.channel() as SocketChannel
            rBuffer.clear()
            count = socketChannel.read(rBuffer)
            //读取数据
            if (count > 0) {
                rBuffer.flip()
                requestMsg = cs.decode(rBuffer).array().contentToString()
            }
            val responseMsg = "已收到客户端的消息: $requestMsg"
            println(responseMsg)
            //返回数据
            sBuffer = ByteBuffer.allocate(responseMsg.toByteArray(cs).size)
            sBuffer.put(responseMsg.toByteArray(cs))
            sBuffer.flip()
            socketChannel.write(sBuffer)
            socketChannel.close()
        }
    }
}

