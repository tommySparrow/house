package com.house.house.mapper;

import com.house.house.common.bean.HouseUser;
import org.apache.ibatis.annotations.Param;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/1
 * @ Description：
 * @ throws
 */
//@Mapper
public interface HouseUserMapper {

    HouseUser selectOneHouseUser(@Param("id") Long id);
}
