package com.apple.product.controller;

import com.apple.common.utils.PageUtils;
import com.apple.common.utils.R;
import com.apple.common.valid.AddGroup;
import com.apple.common.valid.UpdateGroup;
import com.apple.product.entity.BrandEntity;
import com.apple.product.feign.CouponFeignService;
import com.apple.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 品牌
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:24
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private CouponFeignService couponFeignService;

    @RequestMapping("/test")
    public R test(){
        R productcoupon = couponFeignService.productcoupon();
        return R.ok().put("page", productcoupon);
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
//    @RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * @Valid 校验注解
     * BindingResult: 校验结果
     * ------
     * 通过全局异常进行处理
     * -----
     * @Validated 分组校验
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("product:brand:save")
//    public R save(@Valid @RequestBody BrandEntity brand /** BindingResult result **/){
    public R save(@Validated(value = AddGroup.class) @RequestBody BrandEntity brand /** BindingResult result **/){
        // 获取校验结果信息
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            result.getFieldErrors().forEach(item -> {
//                // 错误信息
//                String message = item.getDefaultMessage();
//                // 错误字段
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.error(400, "提交参数不合法").put("data", map);
//        }
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
//		brandService.updateById(brand);
        // 同时对冗余的数据做更新，比如改了品牌名称，其他表的冗余的品牌名做相应的修改
		brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
