package com.apple.order.dao;

import com.apple.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author fengx
 * @email fengx@gmail.com
 * @date 2021-04-18 19:11:46
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
