package com.apple.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

