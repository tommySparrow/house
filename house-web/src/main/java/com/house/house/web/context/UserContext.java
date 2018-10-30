package com.house.house.web.context;

import com.house.house.common.bean.User;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/30
 * @ Description：处理同一线程共享同一变量:ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，
 *                不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量
 * @ throws
 */
public class UserContext {

    private static final ThreadLocal<User> USER_HODLER = new ThreadLocal<>();

    public static void setUser(User user){
        USER_HODLER.set(user);
    }
    public static User getUser(){
       return USER_HODLER.get();
    }
    public static void remove(){
        USER_HODLER.remove();
    }

}
