package com.house.house.mapper;

import com.house.house.common.bean.User;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
//@Mapper
public interface UserMapper {

    /**
     * @ Author jmy
     * @ Description 新建用户
     * @ Date 2018/10/29
     * @ Param [account]
     * @ return int
     **/
    int insert(User account);

    /**
     * @ Author jmy
     * @ Description 查询用户
     * @ Date 2018/10/29
     * @ Param [user]
     * @ return List<User>
     **/
    List<User> selectUsersByQuery(User user);

    /**
     * @ Author jmy
     * @ Description 删除对应用户
     * @ Date 2018/10/29
     * @ Param [email]
     * @ return int
     **/
    int delete(String email);

    /**
     * @ Author jmy
     * @ Description 更新对应用户的属性值//TODO User
     * @ Date 2018/10/29
     * @ Param [user]
     * @ return void
     **/
    void update(User user);
}
