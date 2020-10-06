package com.apple.product.feign;

import com.apple.common.to.SkuFullReductionTo;
import com.apple.common.to.SpuBoundsTo;
import com.apple.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: Feign， 声明式的远程调用接口
 * @author: fengx
 * @create: 2020-09-02 23:28
 */
// 调用的是mall-coupon服务
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @RequestMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundsTo spuBoundsTo);


    @RequestMapping("coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuFullReductionTo skuFullReductionTo);


}
