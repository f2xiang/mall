package com.apple;

import com.apple.product.entity.BrandEntity;
import com.apple.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: fengx
 * @create: 2020-09-01 21:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    BrandService brandService;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("品牌描述");
        brandEntity.setLogo("大脸猫");
        brandEntity.setName("像是");
        brandEntity.setShowStatus(1);
        brandService.save(brandEntity);
        System.out.println("成功");
    }
}
