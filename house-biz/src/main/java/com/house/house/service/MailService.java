package com.house.house.service;

import com.google.common.base.Objects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.house.house.common.bean.User;
import com.house.house.mapper.UserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author keriezhang
 * @ Date       ：Created in 2018/10/27
 * @ Description： 发送邮件相关的方法
 * @ throws
 */
@Service
public class MailService {

    //ip : 端口
    @Value("${domain.name}")
    private String domainName;

    //发送邮件的邮箱
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserMapper userMapper;

    //发送邮件
    @Autowired
    private JavaMailSender mailSender;


    //guava缓存功能(15分钟未处理就会处理相应的onRemoval逻辑)
    private final Cache<String, String> registerCache = CacheBuilder.newBuilder()
            .expireAfterAccess(15, TimeUnit.MINUTES).removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    //缓存的邮箱
                    String email = notification.getValue();
                    User user = new User();
                    user.setEmail(email);
                    //根据邮箱,查出对应的用户
                    List<User> userList = userMapper.selectUsersByQuery(user);
                    //在删除前首先判断用户是否已经被激活，对于未激活的用户进行移除操作
                    if (!userList.isEmpty() && Objects.equal(userList.get(0).getEnable(),0)){
                        //删除对应的用户
                        userMapper.delete(email);
                    }
                }
            }).build();

    /**
     * @ Author jmy
     * @ Description 注册 邮件通知
     *               1.缓存key-email的关系 2.借助spring mail 发送邮件 3.借助异步框架进行异步操作
     * @ Date 2018/10/29
     * @ Param [email]
     * @ return void
     **/
    @Async
    public void registerNotify(String email){
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        //1.缓存key-email的关系(使用guava缓存控件)
        registerCache.put(randomKey,email);
        //发送邮件
        String content = "http://" + domainName + "/accounts/verify?key=" + randomKey;
        sendMail("房产平台密码重置邮件", content, email);
    }
    /**
     * @ Author jmy
     * @ Description //TODO User
     * @ Date 2018/10/29
     * @ Param [title, url, email]
     * @ return void
     **/
    @Async
    public void sendMail(String title, String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setTo(email);
        message.setText(content);
        mailSender.send(message);
    }

}
