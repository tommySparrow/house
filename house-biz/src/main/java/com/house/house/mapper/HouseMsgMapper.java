package com.house.house.mapper;

import com.house.house.common.bean.UserMsg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMsgMapper {

    void insertMsg(UserMsg userMsg);
}
