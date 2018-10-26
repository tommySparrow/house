package com.house.house.web.controller;

import com.house.house.common.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：register user logic of basic
 * @ throws
 */
@Controller
public class UserController {

    /**
     * @ Author : jmyang
     * validate params
     */
    @RequestMapping("/accounts/register")
    public String accountsRegister(User accont, ModelMap modelMap){

        if (null == accont || null == accont.getName()){

            return "/user/accounts/register";
        }
        //用户参数验证
        return null;
    }
}
