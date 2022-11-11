package com.conmu.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 *
 * @Author: zxl
 * @Date: 2020/5/13 23:01
 * @Email: zxl@xxx.com
 * @Description:
 **/
public class RequestBodyChannelFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestBodyChannelFilter.class);


//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
//            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            if ("GET".equals(request.getMethod())) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
            requestWrapper = new RequestBodyWrapper((HttpServletRequest) servletRequest);
        }
        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}