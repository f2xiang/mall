package com.apple.product;

import com.apple.product.entity.BrandEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 
 * @Author: fengx
 * @CreateDate: 2020/9/11 22:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitMqTest {


    @Autowired
    AmqpAdmin amqpAdmin;

    // send message
    @Autowired
    RabbitTemplate rabbitTemplate;



    @Test
    public void sendMessage() {
        // 消息发送可以是一个对象，必须是序列化的，会通过序列化的方式写出去
        // 可以是一串json，需要配置MessageConvent
       // rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", "send a message for java test");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("apple");
        brandEntity.setFirstLetter("a");
        brandEntity.setDescript("its an apple object from java with json message converter");
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", brandEntity);

        log.info("消息发送完成");
    }


    /**
     *  创建交换机
     */

    @Test
    public void createExchange() {
        // 名字， 是否持久化， 是否自动删除
        amqpAdmin.declareExchange(new DirectExchange("hello-java-exchange", true, false));
        log.info("交换机创建完成");
    }

    /**
     *  创建队列
     */

    @Test
    public void createQueue() {
        // 名字， 是否持久化， 是否排他，是否自动删除
        amqpAdmin.declareQueue(new Queue("hello-java-queue",true, false,  false ));
        log.info("队列创建完成");
    }


    /**
     *  创建绑定
     */

    @Test
    public void createBinding() {
        // 目的地【可以是交换机绑交换机，可以是队列】， 目的地类型， 交换机名称，路由建， 参数
        amqpAdmin.declareBinding(new Binding("hello-java-queue", Binding.DestinationType.QUEUE,
                "hello-java-exchange",  "hello.java", null ));
        log.info("完成绑定");
    }
}
