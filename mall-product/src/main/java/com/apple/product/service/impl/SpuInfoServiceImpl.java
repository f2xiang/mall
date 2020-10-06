package com.apple.product.service.impl;

import com.apple.common.to.SkuFullReductionTo;
import com.apple.common.to.SpuBoundsTo;
import com.apple.product.entity.*;
import com.apple.product.feign.CouponFeignService;
import com.apple.product.service.*;
import com.apple.product.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.Query;

import com.apple.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        // 保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        save(spuInfoEntity);

        // spu的描述图片 pms_spu_info_desc
        List<String> decripts = spuSaveVo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(StringUtils.join(decripts, ","));
        spuInfoDescService.save(spuInfoDescEntity);

        // spu的图片集 pms_spu_images
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveBatch(
                images.stream().map(image -> {
                    SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                    spuImagesEntity.setSpuId(spuInfoEntity.getId());
                    spuImagesEntity.setImgUrl(image);
                    return spuImagesEntity;
                }).collect(Collectors.toList())
        );

        // spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        productAttrValueService.saveBatch(
                baseAttrs.stream().map(attr -> {
                    ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
                    productAttrValueEntity.setAttrId(attr.getAttrId());
                    productAttrValueEntity.setAttrValue(attr.getAttrValues());
                    productAttrValueEntity.setAttrName(attrService.getById(attr.getAttrId()).getAttrName());
                    productAttrValueEntity.setQuickShow(attr.getShowDesc());
                    productAttrValueEntity.setSpuId(spuInfoEntity.getId());
                    return productAttrValueEntity;
                }).collect(Collectors.toList())
        );

        // spu的积分信息 sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundsTo spuBoundsTo = new SpuBoundsTo();
        spuBoundsTo.setBuyBounds(bounds.getBuyBounds());
        spuBoundsTo.setGrowBounds(bounds.getGrowBounds());
        spuBoundsTo.setSpuId(spuInfoEntity.getId());
        couponFeignService.saveSpuBounds(spuBoundsTo);


        // spu对于的sku的信息的保存
        // sku的基本信息 pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        skus.forEach(sku -> {
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            BeanUtils.copyProperties(sku, skuInfoEntity);
            skuInfoEntity.setSpuId(spuInfoEntity.getId());
            skuInfoEntity.setCatalogId(spuSaveVo.getCatalogId());
            skuInfoEntity.setBrandId(spuSaveVo.getBrandId());
            skuInfoService.save(skuInfoEntity);


            // sku的图片 pms_sku_images
            List<String> skuImages = sku.getImages();
            skuImagesService.saveBatch(
                    skuImages.stream().map(skuImage -> {
                        SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                        skuImagesEntity.setImgUrl(skuImage);
                        skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                        return skuImagesEntity;
                    }).collect(Collectors.toList())
            );

            // sku的销售属性 pms_sku_sale_attr_value
            List<Attr> attrs = sku.getAttr();
            skuSaleAttrValueService.saveBatch(
                attrs.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList())
            );

            // sku的满减优惠信息
            SkuFullReductionTo skuFullReductionTo = new SkuFullReductionTo();
            skuFullReductionTo.setSkuId(skuInfoEntity.getSkuId());
            couponFeignService.saveSkuReduction(skuFullReductionTo);


        });





    }

}