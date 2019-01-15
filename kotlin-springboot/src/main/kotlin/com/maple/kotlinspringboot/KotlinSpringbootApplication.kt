package com.maple.kotlinspringboot

import com.maple.kotlinspringboot.properties.UserProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(value = [UserProperties::class])
class KotlinSpringbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringbootApplication>(*args)
}

