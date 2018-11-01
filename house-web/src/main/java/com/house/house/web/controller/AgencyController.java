package com.house.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/1
 * @ Description：
 * @ throws
 */
@Controller
public class AgencyController {

    @RequestMapping("agency/list")
    public String agencyList(ModelMap modelMap){


        return "/user/agency/agencyList";
    }

}
