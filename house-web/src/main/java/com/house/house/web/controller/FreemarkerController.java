package com.house.house.web.controller;

import com.house.house.common.bean.Record;
import com.house.house.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FreemarkerController {


    @Autowired
    private RecordService recordService;

    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/10/29
     * @ Param [modelMap]
     * @ return java.lang.String
     **/
    @RequestMapping("/test")
    public String testRecords(ModelMap modelMap){

        List<Record> recordList = recordService.selectRecords();
        Record record = recordList.get(0);
        modelMap.addAttribute("record",record);
        return "hello";
    }

    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/10/29
     * @ Param []
     * @ return java.lang.String
     **/
    @RequestMapping("/index")
    public String index(){

        return "homepage/index";
    }
}
