package com.maple.kotlinspringboot

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.maple.kotlinspringboot.mapper")
class KotlinSpringbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringbootApplication>(*args)
}

