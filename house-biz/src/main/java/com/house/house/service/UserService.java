package com.house.house.service;

import com.house.house.common.bean.User;
import com.house.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入数据
     */
    public boolean addAccout(User accont){


        //插入数据库数据
        userMapper.insert(accont);
        return true;
    }
}
