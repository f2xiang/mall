package com.apple.coupon.dao;

import com.apple.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-01 22:25:11
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
