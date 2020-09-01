package com.apple.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

