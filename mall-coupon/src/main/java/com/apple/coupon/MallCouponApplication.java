package com.apple.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: fengx
 * @create: 2020-08-30 23:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallCouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCouponApplication.class, args);

    }
}
