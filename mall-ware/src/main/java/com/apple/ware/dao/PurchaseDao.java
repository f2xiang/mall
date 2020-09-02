package com.apple.ware.dao;

import com.apple.ware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-02 21:39:41
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
