package com.house.house.web.config;

import com.house.house.web.interceptor.AthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/30
 * @ Description：拦截器配置类
 * @ throws
 */
@Configuration
public class WevMVCconfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AthInterceptor athInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(athInterceptor).excludePathPatterns("/static","/error").addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
