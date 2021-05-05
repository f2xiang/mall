package com.apple.product.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: rabbitmq 配置
 * @Author: fengx
 * @CreateDate: 2020/9/12 9:27
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 给延迟交换机发（路由件）-》延迟队列 （过期） -》 死信交换机 -》死信队列 -》消费者

    /**
     * 延迟交换机
     */
    @Bean
    public Exchange delayExchange() {
        return ExchangeBuilder.directExchange("my_delay_exchange")
                .build();
    }

    /**
     * 延迟交换机 -》 绑定 -》 延迟队列
     */
    @Bean
    public Binding delayBinding(Exchange delayExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue)
                .to(delayExchange)
                .with("routing-5s")
                .noargs();
    }

    /**
     * 延迟队列：接收带过期时间的消息
     * 没人消费进入死信交换机
     *
     * x-dead-letter-exchange = delay_dlx_exchange 死信交换机
     * x-dead-letter-routing-key = routing-5s 路由件
     */
    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable("ttl_5s_queqe")
                .withArgument("x-dead-letter-exchange", "delay_dlx_exchange")
                .withArgument("x-dead-letter-routing-key", "routing-5s")
                .build();
    }



    /**
     * 死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable("dlx_queqe")
                .build();
    }



    /**
     * 死信交换机
     */
    @Bean
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("delay_dlx_exchange")
                .build();
    }


    /**
     * 绑定死信交换机和死信队列
     * 路由件是routing-5s
     * delay_dlx_exchange交换机
     * 那么消息投给dlx_queqe
     */
    @Bean
    public Binding dlxBinding(Exchange dlxExchange, Queue dlxQueue) {
        return BindingBuilder.bind(dlxQueue)
                .to(dlxExchange)
                .with("routing-5s")
                .noargs();
    }

}
