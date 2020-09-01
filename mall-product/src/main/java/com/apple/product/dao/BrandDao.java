package com.apple.product.dao;

import com.apple.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
