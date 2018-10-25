package com.house.house.mapper;

import com.house.house.common.bean.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> selectRecords();
}