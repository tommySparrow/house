package com.house.house.web.interceptor;

import com.house.house.common.bean.User;
import com.house.house.web.context.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/30
 * @ Description：未登陆账号处理逻辑
 * @ throws
 */
@Component
public class AuthActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从threadLocal中取出当前对象
        User user = UserContext.getUser();
        if (null == user){
            //未登陆-->跳转到登陆页面
            String msg = URLEncoder.encode("请先登陆!", "utf-8");
            String target = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");

            if ("get".equalsIgnoreCase(request.getMethod())){
                //跳转到登陆页面
                response.sendRedirect("/accounts/signin?errorMsg=" + msg + "&target="+target);
                return false;
            } else {
                //跳转到登陆页面
                response.sendRedirect("/accounts/signin?errorMsg="+msg);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
