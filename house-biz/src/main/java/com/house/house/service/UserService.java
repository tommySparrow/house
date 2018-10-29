package com.house.house.service;

import com.google.common.collect.Lists;
import com.house.house.common.bean.User;
import com.house.house.common.utils.HashUtils;
import com.house.house.common.validate.BeanHelper;
import com.house.house.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.prefix}")
    private String imgPrefix;

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
/**
 * @ Author jmy
 * @ Description 查询对应的用户//TODO User
 * @ Date 2018/10/29
 * @ Param [username, password]
 * @ return com.house.house.common.bean.User
 **/
    public User ath(String username, String password) {

        User user = new User();
        user.setEmail(username);
        user.setPasswd(HashUtils.encryPassword(password));
        user.setEnable(1);
        List<User> userList = getUserByQuery(user);
        if (!userList.isEmpty()){
           return userList.get(0);
        }
        return null;
    }

    /**
     * @ Author jmy
     * @ Description 查询对应的用户//TODO User
     * @ Date 2018/10/29
     * @ Param [user]
     * @ return void
     **/
    private List<User> getUserByQuery(User user) {
        List<User> userList = userMapper.selectUsersByQuery(user);
        userList.forEach(u -> {
            u.setAvatar(imgPrefix + u.getAvatar());
        });
        return userList;
    }
}
