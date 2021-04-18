package com.apple.order.feign;

import com.apple.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 库存调用
 * @author: fengx
 * @create: 2021-04-18 19:35
 */
@FeignClient("mall-wms")
public interface WareFeignService {

    @RequestMapping("ware/waresku/reduce")
    R reduce(@RequestBody String skuId);


}
