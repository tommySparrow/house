package com.house.house.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("request.....incoming");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
