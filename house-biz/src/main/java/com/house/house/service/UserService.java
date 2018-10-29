package com.house.house.service;

import com.google.common.collect.Lists;
import com.house.house.common.bean.User;
import com.house.house.common.utils.HashUtils;
import com.house.house.common.validate.BeanHelper;
import com.house.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;
    @Autowired
    private MailService mailService;

    /**
     * @ Author jmy
     * @ Description 插入数据
     * @ Date 2018/10/29
     * @ Param [account]
     * @ return boolean
     **/
    @Transactional
    public boolean addAccout(User account){

        //密码加密
        String encryPassword = HashUtils.encryPassword(account.getPasswd());
        account.setPasswd(encryPassword);
        //上传头像图片file路径管理
        List<String> imgPaths = fileService.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
        String imgPath = imgPaths.get(0);
        account.setAvatar(imgPath);

        BeanHelper.setDefaultProp(account,User.class );
        BeanHelper.onInsert(account);

        account.setEnable(0);
        //插入数据库数据
        userMapper.insert(account);
        //邮件通知
        mailService.registerNotify(account.getEmail());
        return true;
    }

    /**
     * @ Author jmy
     * @ Description 根据参数key 1.修改数据库中改用户的状态吗 2.失效cache中的对应数据 //TODO User
     * @ Date 2018/10/29
     * @ Param [key]
     * @ return void
     **/
    public boolean enable(String key) {
        return mailService.enable(key);
    }
}
