package com.house.house.web.controller;

import com.house.house.common.bean.House;
import com.house.house.common.bean.HouseUser;
import com.house.house.common.bean.UserMsg;
import com.house.house.common.constants.CommonConstants;
import com.house.house.common.page.PageData;
import com.house.house.common.page.PageParams;
import com.house.house.common.result.ResultMsg;
import com.house.house.service.AgencyService;
import com.house.house.service.HouseService;
import com.house.house.service.HouseUserService;
import com.house.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @Autowired
    private HouseUserService houseUserService;

    @Autowired
    private AgencyService agencyService;

    //热门房产
    @Autowired
    private RecommendService recommendService;



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
        List<House> hotHouses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", hotHouses);
        modelMap.put("ps", housePageData);
        modelMap.put("vo", house);
        return "house/listing";
    }

    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/11/1
     * @ Param [id, modelMap]
     * @ return java.lang.String
     * 查询房屋详情
     * 查询关联经纪人
     **/
    @RequestMapping("/house/detail")
    public String houseDetail(Long id,ModelMap modelMap){

        //根据id查询对应房产信息
        House house = houseService.queryOneHouse(id);
        //根据id查询house_use 关联表(type = 1)
        HouseUser houseUser = houseUserService.selectOneHouseUser(id);
        //查询房屋详情的时候,将房屋id添加到Redis中存储
        recommendService.increase(id);
        if ( null != houseUser.getUserId() && !houseUser.getUserId().equals(0)){
            //获取经纪人信息
            modelMap.put("agent", agencyService.getAgentDeail(houseUser.getUserId()));
        }
        //获取热门房产
        List<House> rcHouses =  recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", rcHouses);
        modelMap.put("house", house);
        return "/house/detail";
    }

    /**
     * @ Author jmy
     * @ Description 留言//TODO User
     * @ Date 2018/11/1
     * @ Param [userMsg]
     * @ return java.lang.String
     **/
    @RequestMapping("/house/leaveMsg")
    public String houseMsg(UserMsg userMsg){
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId()+"&"+ ResultMsg.successMsg("留言成功").asUrlParams();
    }

}
