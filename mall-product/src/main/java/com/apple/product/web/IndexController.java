package com.apple.product.web;

import com.apple.product.entity.CategoryEntity;
import com.apple.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @description: 页面的首页
 * @author: fengx
 * @create: 2020-10-06 19:53
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
        List<CategoryEntity> list = categoryService.getLevel1CategoryList();
        model.addAttribute("categorys", list);
        return "index";
    }


    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Object getCategoryJson() {
        return categoryService.getCategoryJson();
    }
}
