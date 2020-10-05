package com.apple.product.service;

import com.apple.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.product.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params, Long catelogId);
    PageUtils queryScalePage(Map<String, Object> params, Long catelogId);

    void saveAttr(AttrVo attr);

    List<AttrEntity> getListByAttrGroup(Long attrGroupId, int type);
}

