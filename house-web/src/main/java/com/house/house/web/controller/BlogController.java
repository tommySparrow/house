package com.house.house.web.controller;

import com.house.house.common.bean.Blog;
import com.house.house.common.bean.House;
import com.house.house.common.constants.CommonConstants;
import com.house.house.common.page.PageData;
import com.house.house.common.page.PageParams;
import com.house.house.service.BlogService;
import com.house.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：博客
 * @ throws
 */
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value="blog/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(Integer pageSize, Integer pageNum, Blog blog, ModelMap modelMap){
        PageData<Blog> ps = blogService.queryBlog(blog, PageParams.build(pageSize, pageNum));
        List<House> houses =  recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("ps", ps);
        return "/blog/listing";
    }
}
