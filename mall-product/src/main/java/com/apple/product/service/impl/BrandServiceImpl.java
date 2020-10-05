package com.apple.product.service.impl;

import com.apple.product.dao.CategoryBrandRelationDao;
import com.apple.product.entity.AttrEntity;
import com.apple.product.service.CategoryBrandRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;

import com.apple.product.dao.BrandDao;
import com.apple.product.entity.BrandEntity;
import com.apple.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@RabbitListener(queues = {"hello-java-queue"})
@Service("brandService")
@Slf4j
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;


    // ------------mq listener test--------

    @RabbitHandler
    public void getMqMessage(BrandEntity brandEntity) {
        log.info(brandEntity.toString());
    }

    @RabbitHandler
    public void getMqMessage(AttrEntity attrEntity) {
        log.info(attrEntity.toString());
    }
    // --------------mq listener test-----



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object key = params.get("key");
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("name", key).or().like("descript", key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(BrandEntity brand) {
        updateById(brand);
        // 修改其他表的冗余字段
        categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

    }

}