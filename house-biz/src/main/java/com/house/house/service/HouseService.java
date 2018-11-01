package com.house.house.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.house.house.common.bean.Community;
import com.house.house.common.bean.House;
import com.house.house.common.bean.User;
import com.house.house.common.bean.UserMsg;
import com.house.house.common.page.PageData;
import com.house.house.common.page.PageParams;
import com.house.house.common.validate.BeanHelper;
import com.house.house.mapper.HouseMapper;
import com.house.house.mapper.HouseMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/31
 * @ Description：
 * @ throws
 */
@Service
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Value("${file.prefix}")
    private String imgPrefix;

    @Autowired
    private HouseMsgMapper houseMsgMapper;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private MailService mailService;

    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/10/31
     * @ Param [house, pageParams]
     * @ return com.house.house.common.page.PageData<com.house.house.common.bean.House>
     * 1.查询小区
     * 2.添加图片服务器地址前缀
     * 3.构建分页结果
     **/
    public PageData<House> queryHouse(House house, PageParams pageParams) {

        //注意:这里使用house.name接收了小区名称(community.name)
        List<House> houses = Lists.newArrayList();
        //判断房产名称是否为空
        if (!Strings.isNullOrEmpty(house.getName())){

            Community community = new Community();
            community.setName(house.getName());
            //根据房产名称查询对应的小区信息
            List<Community> communities = houseMapper.selectCommunity(community);
            if (!communities.isEmpty()){
                //设置小区id
                house.setCommunityId(communities.get(0).getId());
            }
        }
        //添加图片服务器地址前缀
        houses = queryAndSetImg(house,pageParams);
        Long count = houseMapper.selectPageCount(house);
        return PageData.buildPage(houses, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    private List<House> queryAndSetImg(House house, PageParams pageParams) {
        //分页查询房产
        List<House> houseList = houseMapper.selectPageHouses(house, pageParams);
        houseList.forEach(h ->{
            h.setFirstImg(imgPrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
        });
        return houseList;
    }

    /**
     * @ Author jmy
     * @ Description 查询房产信息//TODO User
     * @ Date 2018/11/1
     * @ Param [id]
     * @ return com.house.house.common.bean.House
     **/
    public House queryOneHouse(Long id) {

        House house = new House();
        house.setId(id);
        List<House> houses = queryAndSetImg(house, PageParams.build(1, 1));
        if (houses.size() != 0){
            return houses.get(0);
        }
        return null;
    }

    public void addUserMsg(UserMsg userMsg) {

        BeanHelper.onInsert(userMsg);
        //插入house_msg数据
        houseMsgMapper.insertMsg(userMsg);
        //根据经纪机构id,查询对应经纪机构
        Long agentId = userMsg.getAgentId();
        User agentDeail = agencyService.getAgentDeail(agentId);
        mailService.sendMail("来自用户"+userMsg.getEmail()+"的留言", userMsg.getMsg(), agentDeail.getEmail());
    }
}
