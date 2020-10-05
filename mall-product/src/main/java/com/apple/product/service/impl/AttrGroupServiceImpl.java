package com.apple.product.service.impl;

import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;
import com.apple.product.dao.AttrGroupDao;
import com.apple.product.entity.AttrGroupEntity;
import com.apple.product.service.AttrGroupService;
import com.apple.product.service.AttrService;
import com.apple.product.service.CategoryService;
import com.apple.product.vo.AttrGroupWithAttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * categoryId是0 就查询所有的
     * 反之 就查询指定的id下的属性分组
     * params 携带一个查询的key 进行模糊匹配
     */
    @Override
    public PageUtils queryPageByCategoryId(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();

        Object key = params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("attr_group_name", key)
                    .or()
                    .like("descript", key);
        }

        if (0 != categoryId) {
            wrapper.and(item -> item.eq("catelog_id", categoryId));
        }

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public AttrGroupEntity getByIdWithCategoryPath(Long attrGroupId) {
        AttrGroupEntity entity = getById(attrGroupId);
        entity.setCatelogPath(categoryService.getCategoryPath(entity.getCatelogId()));
        return entity;
    }

    @Override
    public Object getListByCategoryId(Long catId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelog_id", catId);
        List<AttrGroupEntity> attrGroupEntities = baseMapper.selectList(wrapper);

        return attrGroupEntities.stream().map(item -> {
            // 创建vo
            AttrGroupWithAttrVo attrGroupWithAttrVo = new AttrGroupWithAttrVo();
            BeanUtils.copyProperties(item, attrGroupWithAttrVo);
            attrGroupWithAttrVo.setAttrs(attrService.getListByAttrGroup(item.getAttrGroupId(), 0));
            return attrGroupWithAttrVo;
        });

    }

}