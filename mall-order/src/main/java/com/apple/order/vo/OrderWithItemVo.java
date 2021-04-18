package com.apple.order.vo;

import com.apple.order.entity.OrderEntity;
import com.apple.order.entity.OrderItemEntity;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: fengx
 * @create: 2021-04-18 20:03
 */
@Data
public class OrderWithItemVo extends OrderEntity {
    private List<OrderItemEntity> items;
}
