package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.example.demo.service.SocketServer



@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    //起socket服务
    val server = SocketServer()
    server.startSocketServer(9001)
    println("启动socket服务完成 端口号:9001")
}
