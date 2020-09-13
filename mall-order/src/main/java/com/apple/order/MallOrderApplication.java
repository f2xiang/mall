package com.apple.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用ampq，RabbitAutoConfiguration自动生效
 */
@SpringBootApplication
public class MallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }
}
