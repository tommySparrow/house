package com.house.house.service;

import com.house.house.common.bean.Agency;
import com.house.house.common.bean.User;
import com.house.house.common.page.PageParams;
import com.house.house.mapper.AgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/1
 * @ Description： 代理商业务
 * @ throws
 */
@Service
public class AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.prefix}")
    private String imgPrefix;
    /**
     * @ Author jmy
     * @ Description 访问user表获取详情 添加用户头像//TODO User
     * @ Date 2018/11/1
     * @ Param [userId]
     * @ return com.house.house.common.bean.User
     **/
    public User getAgentDeail(Long userId) {

        User user = new User();
        user.setId(userId);
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, PageParams.build(1, 1));
        if (!list.isEmpty()){
            setImg(list);
            User agent = list.get(0);
            //将经纪人关联的经纪机构也一并查询出来
            Agency agency = new Agency();
            agency.setId(agent.getAgencyId().intValue());
            List<Agency> agencies = agencyMapper.selectByAgency(agency);
            if (!agencies.isEmpty()){
                //经纪人机构名称
                agent.setAgencyName(agencies.get(0).getName());
            }
            return agent;
        }

        return null;
    }

    //添加域名
    private void setImg(List<User> list) {
        list.forEach(i -> {
            i.setAvatar(imgPrefix + i.getAvatar());
        });

    }
}
