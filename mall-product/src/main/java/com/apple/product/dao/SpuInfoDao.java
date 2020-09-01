package com.apple.product.dao;

import com.apple.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 * 
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
	
}
