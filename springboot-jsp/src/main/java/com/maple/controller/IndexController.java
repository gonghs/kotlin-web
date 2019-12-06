package com.maple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 *
 * @author gonghs
 * @version 1.0
 * @since 2019-12-05 17:03
 */
@Controller
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("bool", true);
        return "index";
    }
}
