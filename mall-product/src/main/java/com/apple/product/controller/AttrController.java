package com.apple.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.apple.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apple.product.entity.AttrEntity;
import com.apple.product.service.AttrService;
import com.apple.common.utils.PageUtils;
import com.apple.common.utils.R;



/**
 * 商品属性
 *
 * @author fenx
 * @email fengx@gmail.com
 * @date 2020-09-01 21:11:23
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 规格属性列表
     */
    @RequestMapping("/base/list/{catelogId}")
 //   @RequiresPermissions("product:attr:list")
    public R baselist(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId){
        params.put("type", 1);
        PageUtils page = attrService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }

    /**
     * 销售属性列表
     */
    @RequestMapping("/sale/list/{catelogId}")
 //   @RequiresPermissions("product:attr:list")
    public R scaleList(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId){
        PageUtils page = attrService.queryScalePage(params, catelogId);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
//    @RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
