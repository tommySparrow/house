package com.house.house.service;

import com.google.common.collect.Lists;
import com.house.house.common.bean.User;
import com.house.house.common.utils.HashUtils;
import com.house.house.common.validate.BeanHelper;
import com.house.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 插入数据
     */
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
        return true;
    }
}
