package com.house.house.web.interceptor;

import com.google.common.base.Joiner;
import com.house.house.common.bean.User;
import com.house.house.common.constants.CommonConstants;
import com.house.house.web.context.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/30
 * @ Description：静态资源拦截器
 * @ throws
 */
@Component
public class AthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k,v)->{
                    if ("errorMsg".equals(k) || "successMsg".equals(k) || "target".equals(k)){
                        request.setAttribute(k, Joiner.on(",").join(v));
                    }
                });
        //静态资源放行
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/static") || requestURI.startsWith("/error")){
            return true;
        }
        //非静态资源处理
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if (null !=user){
            UserContext.setUser(user);
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
