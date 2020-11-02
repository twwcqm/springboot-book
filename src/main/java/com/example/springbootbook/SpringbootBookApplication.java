package com.example.springbootbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//使用注解驱动缓存机制
@EnableCaching
//开启基于注解的RabbitMQ模式
@EnableRabbit
//指定扫描 MyBatis Mapper
@MapperScan
public class SpringbootBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookApplication.class, args);
    }

}
