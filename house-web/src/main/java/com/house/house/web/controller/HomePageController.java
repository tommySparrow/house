package com.house.house.web.controller;

import com.house.house.common.bean.House;
import com.house.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：首页显示
 * @ throws
 */
@Controller
public class HomePageController {

    @Autowired
    private RecommendService recommendService;

    //访问域名跳转到首页
    @RequestMapping("")
    public String index(ModelMap modelMap){

        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String realIndex(ModelMap modelMap){

        //获取最新添加的房产信息
        List<House> lastest = recommendService.getLastest();

        modelMap.addAttribute("recomHouses", lastest);
        return "/homepage/index";
    }

}
