package com.apple.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

