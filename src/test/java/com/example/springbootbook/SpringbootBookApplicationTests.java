package com.example.springbootbook;

import com.example.springbootbook.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootBookApplicationTests {

    //注入由 Spring Boot 自动配置的 Rabbit Template
    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 单播(点对点)
     */
    @Test
    void contextLoads() {
        Book book = new Book();
        book.setAuthor("无名氏");
        book.setName("java天下第一");
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("1","java");
        hashMap.put("2","c#");
        hashMap.put("3",true);
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",hashMap);
        System.out.println("消息发送成功");
    }

    /**
     * 接收数据
     */
    @Test
    void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     * 发送书籍消息
     */
    @Test
    void SendBook(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book());
    }

}
