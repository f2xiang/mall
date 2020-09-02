package com.apple.member.dao;

import com.apple.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-02 21:36:11
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
