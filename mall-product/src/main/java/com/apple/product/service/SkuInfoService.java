package com.apple.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

