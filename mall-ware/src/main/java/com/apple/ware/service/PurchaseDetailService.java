package com.apple.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-02 21:39:41
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

