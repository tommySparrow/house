package com.house.house.web.controller;

import com.house.house.common.bean.User;
import com.house.house.common.result.ResultMsg;
import com.house.house.common.validate.UserHelper;
import com.house.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    /**
     * @ Author jmy
     * @ Description 用户注册//TODO User
     * @ Date 2018/10/29
     * @ Param [accont, modelMap]
     * @ return java.lang.String
     **/
    @RequestMapping("/accounts/register")
    public String accountsRegister(User accont, ModelMap modelMap){

        if (null == accont || null == accont.getName()){

            return "/user/accounts/register";
        }
        //用户参数验证
        ResultMsg resultMsg = UserHelper.validate(accont);
        if (resultMsg.isSuccess() && userService.addAccout(accont)){

            modelMap.addAttribute("email", accont.getEmail());
            return "/user/accounts/registerSubmit";
        }else {
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }
    }

    /**
     * @ Author jmy
     * @ Description 激活账号//TODO User
     * @ Date 2018/10/29
     * @ Param [key]
     * @ return java.lang.String
     **/

    @RequestMapping("/accounts/verify")
    public String verify(String key){

        //根据参数key 1.修改数据库中改用户的状态吗 2.失效cache中的对应数据
        boolean res = userService.enable(key);
        if (res){
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        }else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败,请确认链接是否过期");
        }

    }
}
