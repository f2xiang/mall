package com.apple.product.feign;

import com.apple.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: Feign， 声明式的远程调用接口
 * @author: fengx
 * @create: 2020-09-02 23:28
 */
// 调用的是mall-coupon服务
@FeignClient("gateway-coupon")
public interface CouponFeignService {

    @RequestMapping("coupon/coupon/product/coupon")
    R productcoupon();


}
