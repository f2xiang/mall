package com.apple.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

