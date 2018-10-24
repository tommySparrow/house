package com.house.house.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterBeanConfig {


    /**
     * 1.构造filter
     * 2.配置拦截urlPattern
     * 3.利用FilterRegistrationBean进行包装
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        //包装filter
        filterRegistrationBean.setFilter(new LogFilter());


        List<String> list = new ArrayList<String>();
        //设置拦截路径
        list.add("*");
        filterRegistrationBean.setUrlPatterns(list);
        return filterRegistrationBean;
    }
}
