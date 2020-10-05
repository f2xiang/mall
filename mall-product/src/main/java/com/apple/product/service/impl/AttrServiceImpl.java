package com.apple.product.service.impl;

import com.apple.product.dao.AttrAttrgroupRelationDao;
import com.apple.product.dao.AttrGroupDao;
import com.apple.product.dao.CategoryDao;
import com.apple.product.entity.AttrAttrgroupRelationEntity;
import com.apple.product.entity.AttrGroupEntity;
import com.apple.product.entity.CategoryEntity;
import com.apple.product.vo.AttrRespVo;
import com.apple.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;

import com.apple.product.dao.AttrDao;
import com.apple.product.entity.AttrEntity;
import com.apple.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Resource
    private AttrGroupDao attrGroupDao;

    @Resource
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", 1); // 0 基本属性

        // 查询所有
        if (catelogId != 0) {
            wrapper.and(item -> item.eq("catelog_id", catelogId));
        }

        Object key = params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(item -> item.eq("attr_name", key));
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), wrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrRespVo> voList = page.getRecords().stream().map(attrEntity -> {
            // 创建vo
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            // 分类名称
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            // 分组名称
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao
                    .selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrRespVo.getAttrId()));
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
            attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            attrRespVo.setCatelogName(categoryEntity.getName());
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(voList);
        return pageUtils;
    }

    @Override
    public PageUtils queryScalePage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", 0); // 0 销售属性
        // 查询所有
        if (catelogId != 0) {
            wrapper.and(item -> item.eq("catelog_id", catelogId));
        }

        Object key = params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(item -> item.eq("attr_name", key));
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), wrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrRespVo> voList = page.getRecords().stream().map(attrEntity -> {
            // 创建vo
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            // 分类名称
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            // 分组名称
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao
                    .selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrRespVo.getAttrId()));
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
            attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            attrRespVo.setCatelogName(categoryEntity.getName());
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(voList);
        return pageUtils;
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }

    @Override
    public List<AttrEntity> getListByAttrGroup(Long attrGroupId, int type) {
        // 分组名称
        List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationDao
                .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));

        return relationEntities.stream()
                .map(item -> baseMapper.selectById(item.getAttrId()))
                .filter(item -> type !=item.getAttrType())
                .collect(Collectors.toList());
    }

}