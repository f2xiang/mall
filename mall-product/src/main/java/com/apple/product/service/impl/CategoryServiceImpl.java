package com.apple.product.service.impl;

import com.apple.product.service.CategoryBrandRelationService;
import com.apple.product.vo.Catalog2Vo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;

import com.apple.product.dao.CategoryDao;
import com.apple.product.entity.CategoryEntity;
import com.apple.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

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



    // 现在开发大部分都是逻辑删除
    //      用一个标志位来区分

    // deleteBatchIds为物理删除，删了就没了

    @Override
    public void removeMenuByIds(List<Long> ids) {
        // TODO 1检查菜单有没有被其他的地方引用

        // 2没有就删除
        baseMapper.deleteBatchIds(ids);
    }

    // 获取分类的完整路径
    @Override
    public List<Long> getCategoryPath(Long catelogId) { // 225
        List<Long> list = new ArrayList<>();
        list.add(catelogId);
        CategoryEntity categoryEntity = baseMapper.selectById(catelogId); //225
        while (categoryEntity.getParentCid() != 0) {
            list.add(categoryEntity.getParentCid());
            categoryEntity = baseMapper.selectById(categoryEntity.getParentCid() );
        }
        Collections.reverse(list);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(CategoryEntity category) {
        updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    @Override
    public List<CategoryEntity> getLevel1CategoryList() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }

    @Override
    public Map<Long, Object> getCategoryJson() {
        // 所有分类数据
        List<CategoryEntity> allCategorys = baseMapper.selectList(null);

        // 一级分类
        List<CategoryEntity> category1List = getListByParentCid(allCategorys, 0);

        return category1List.stream().collect(Collectors.toMap(CategoryEntity::getCatId, item -> {
            // 根据一级分类去查二级和三级
            List<CategoryEntity> category2List = getListByParentCid(allCategorys, item.getCatId());

            return category2List.stream().map(category2item -> {
                // 获取三级的分类
                List<CategoryEntity> category3List = getListByParentCid(allCategorys, category2item.getCatId());

                // 封装三级的分类到二级分类
                return new Catalog2Vo(
                        item.getCatId(),
                        category2item.getCatId(),
                        category2item.getName(),
                        category3List == null? null : category3List.stream().map(category3item -> new Catalog2Vo.Catalog3Vo(category2item.getCatId(), category3item.getCatId(), category3item.getName())).collect(Collectors.toList())
                );
            }).collect(Collectors.toList());
        }));
    }

    /**
     * 在list里挑选parentId 为 catId的数据
     * @param allCategorys 所有的分类数据
     * @param catId 数据筛选的条件 pid 为这个值
     */
    private List<CategoryEntity> getListByParentCid(List<CategoryEntity> allCategorys, long catId) {
        return allCategorys.stream().filter(item -> item.getParentCid() == catId).collect(Collectors.toList());
    }

}