package com.apple.product.controller;

import com.apple.product.entity.AttrEntity;
import com.apple.product.entity.BrandEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 
 * @Author: fengx
 * @CreateDate: 2020/9/12 10:02
 */
@RestController
@Slf4j
public class RabbitController {

    // send message
    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/send/message")
    public String sendMessage() {
//        for(int i = 0; i < 10; i++) {
//            if(i % 2 == 0) {
//                BrandEntity brandEntity = new BrandEntity();
//                brandEntity.setName("apple");
//                brandEntity.setFirstLetter("a");
//                brandEntity.setDescript("its an apple object from java with json message converter");
//                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", brandEntity);
//            }else {
//                AttrEntity attrEntity = new AttrEntity();
//                attrEntity.setAttrName("apple attr");
//                attrEntity.setAttrType(1);
//                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", attrEntity);
//            }
//        }

        Map<String, String> map = new HashMap<>();
        map.put("msg", "hello");
        rabbitTemplate.convertAndSend("my_delay_exchange", "routing-5s", map , new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });

        log.info("消息发送完成,当前时间是{}", new Date());
        return "ok";
    }


}
