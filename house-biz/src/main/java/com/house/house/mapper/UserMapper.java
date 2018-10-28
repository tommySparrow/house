package com.house.house.mapper;

import com.house.house.common.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
@Mapper
public interface UserMapper {


    /**
     * 新建用户
     */
    int insert(User account);

    /**
     * 查询用户
     */
    List<User> selectUsersByQuery(User user);

    /**
     * 删除对应用户
     */
    int delete(String email);
}
