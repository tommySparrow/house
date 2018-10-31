package com.house.house.mapper;

import com.house.house.common.bean.Community;
import com.house.house.common.bean.House;
import com.house.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {

     List<Community> selectCommunity(Community community);

    List<House> selectPageHouses(@Param("house") House house, @Param("pageParams")PageParams pageParams);

    Long selectPageCount(@Param("house")House house);
}
