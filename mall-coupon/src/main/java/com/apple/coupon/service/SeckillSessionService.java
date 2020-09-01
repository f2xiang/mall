package com.apple.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-01 22:25:11
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

