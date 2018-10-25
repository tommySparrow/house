package com.house.house.web.controller;

import com.house.house.common.bean.Record;
import com.house.house.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/24
 * @ Description：
 * @ throws
 */
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/getRecords")
    public List<Record> selectRecords(){

        List<Record> recordList = recordService.selectRecords();
        return recordList;
    }
}
