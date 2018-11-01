package com.house.house.mapper;

import com.house.house.common.bean.Agency;
import com.house.house.common.bean.User;
import com.house.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {

    List<User> selectAgent(@Param("user") User user, @Param("pageParams")PageParams pageParams);

    List<Agency> selectByAgency(@Param("agency")Agency agency);
}
