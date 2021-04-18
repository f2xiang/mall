package com.apple.order.service;

import com.apple.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;

import java.util.Map;

/**
 * 订单
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2021-04-18 19:11:46
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

