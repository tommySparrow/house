package com.house.house.common.validate;

import com.house.house.common.bean.User;
import com.house.house.common.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：validate user param
 * @ throws
 */
public class UserHelper {

    public static ResultMsg validate(User account) {

        if (StringUtils.isBlank(account.getName())) {
            ResultMsg.errorMsg("Name 不得为空");
        }
        if (StringUtils.isBlank(account.getEmail())) {
            ResultMsg.errorMsg("Email 不得为空");
        }
        if (StringUtils.isBlank(account.getPhone())) {
            ResultMsg.errorMsg("Phone 不得为空");
        }
        if (StringUtils.isBlank(account.getPasswd()) || StringUtils.isBlank(account.getConfirmPasswd())
                || !account.getPasswd().equals(account.getConfirmPasswd())) {
            ResultMsg.errorMsg("Passwd 有误");
        }
        if (account.getPasswd().length() < 6){
            ResultMsg.errorMsg("Passwd 长度未大于6位");
        }
        return ResultMsg.successMsg("");
    }
}
