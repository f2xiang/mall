package com.apple.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-01 22:25:11
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

