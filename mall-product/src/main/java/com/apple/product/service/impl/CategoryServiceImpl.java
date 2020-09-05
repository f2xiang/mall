package com.apple.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;

import com.apple.product.dao.CategoryDao;
import com.apple.product.entity.CategoryEntity;
import com.apple.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> getTreeList() {
        List<CategoryEntity> list = baseMapper.selectList(null);
        return list.stream()
                // 过滤父
                .filter(item -> item.getParentCid() == 0)
                // 映射子
                .map(item -> {
                    item.setChildren(getChildren(item, list));
                    return item;
                })
                // 排序
                .sorted((item1, item2) ->
                        (item2.getSort() == null? 0: item2.getSort())  - (item1.getSort() == null? 0: item1.getSort()))
                // 数据收集
                .collect(Collectors.toList());
    }


    /**
     * 获取某一个菜单的子菜单
     * @param root 父菜单
     * @param all  所有菜单
     * @return 子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
                // 递归 获取 这个分类下的子分类
                .map(item -> {
                    item.setChildren(getChildren(item, all));
                    return item;
                })
                .sorted((item1, item2) ->
                        (item2.getSort() == null? 0: item2.getSort())  - (item1.getSort() == null ? 0: item1.getSort()))
                .collect(Collectors.toList());
    }

}