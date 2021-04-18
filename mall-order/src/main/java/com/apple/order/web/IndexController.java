package com.apple.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description:
 * @author: fengx
 * @create: 2021-04-18 18:53
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
//        List<CategoryEntity> list = categoryService.getLevel1CategoryList();
//        model.addAttribute("categorys", list);
        return "index";
    }


}
