package com.house.house.mapper;

import com.house.house.common.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
@Mapper
public interface UserMapper {

    public int insert(User account);
}
