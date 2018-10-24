package com.house.house.controller;

import com.house.house.bean.Record;
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

    @RequestMapping("/test")
    public String testRecords(ModelMap modelMap){

        List<Record> recordList = recordService.selectRecords();
        Record record = recordList.get(0);
        modelMap.addAttribute("record",record);
        return "hello";
    }

    @RequestMapping("/index")
    public String index(){

        return "homepage/index";
    }
}
