package com.house.house.web.controller;

import com.house.house.common.bean.House;
import com.house.house.common.page.PageData;
import com.house.house.common.page.PageParams;
import com.house.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/31
 * @ Description：
 * @ throws
 */
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/10/31
     * @ Param [pageSize, pageNum, house, modelMap]
     * @ return java.lang.String
     * 1.实现分页
     * 2.支持小区搜索、类型搜索
     * 3.支持排序
     * 4.支持展示图片、价格、标题、地址等信息
     **/
    @RequestMapping("/house/list")
    public String houseList(Integer pageSize, Integer pageNum, House house, ModelMap modelMap){

        //查询房屋信息数据(带分页)
        PageData<House> housePageData = houseService.queryHouse(house, PageParams.build(pageSize, pageNum));
        modelMap.put("ps", housePageData);
        modelMap.put("vo", house);
        return "house/listing";
    }

}
