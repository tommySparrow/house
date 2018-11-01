package com.house.house.web.controller;

import com.house.house.common.bean.Agency;
import com.house.house.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/1
 * @ Description：
 * @ throws
 */
@Controller
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/agency/list")
    public String agencyList(ModelMap modelMap){

        List<Agency> agencyList = agencyService.getAllAgency();
        modelMap.addAttribute("agencyList", agencyList);
        return "/user/agency/agencyList";
    }

}
