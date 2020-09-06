package com.apple.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: MyBatis配置
 * @author: fengx
 * @create: 2020-09-06 17:38
 */
@Configuration
public class MyBatisConfig {

    // 分页配置
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 默认false， 最大页数超了，回到首页
        paginationInterceptor.setOverflow(true);
        // 最大单页限制数量， 默认500， -1 不限制
        paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }
}
