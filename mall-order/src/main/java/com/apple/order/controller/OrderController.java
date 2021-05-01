package com.apple.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.apple.order.entity.OrderEntity;
import com.apple.order.feign.WareFeignService;
import com.apple.order.service.OrderService;
import com.apple.order.vo.OrderWithItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.apple.common.utils.PageUtils;
import com.apple.common.utils.R;



/**
 * 订单
 *
 * @author fengx
 * @email fengx@gmail.com
 * @date 2021-04-18 19:11:46
 */
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private WareFeignService wareFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("coupon:omsorder:list")
    @SentinelResource
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("coupon:omsorder:info")
    public R info(@PathVariable("id") Long id){
		OrderEntity omsOrder = orderService.getById(id);

        return R.ok().put("omsOrder", omsOrder);
    }

    /**
     * 下订单
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("coupon:omsorder:save")
    public R save(@RequestBody OrderWithItemVo order){
		orderService.save(order);
		wareFeignService.reduce(order.getItems().get(0).getSkuId()+"");

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("coupon:omsorder:update")
    public R update(@RequestBody OrderEntity omsOrder){
		orderService.updateById(omsOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("coupon:omsorder:delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
