package com.apple.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.apple.common.utils.PageUtils;
import com.apple.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2020-09-02 21:36:11
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

