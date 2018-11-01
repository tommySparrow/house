package com.house.house.service;

import com.house.house.common.bean.HouseUser;
import com.house.house.mapper.HouseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/1
 * @ Description：house_user 关联业务
 * @ throws
 */
@Service
public class HouseUserService {

    @Autowired
    private HouseUserMapper houseUserMapper;

    public HouseUser selectOneHouseUser(Long id) {

        return  houseUserMapper.selectOneHouseUser(id);
    }
}
