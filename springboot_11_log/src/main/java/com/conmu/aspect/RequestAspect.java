package com.conmu.aspect;


import com.alibaba.fastjson.JSONObject;
import com.conmu.annotation.ResponseLog;
import com.conmu.exception.AppRuntimeException;
import com.conmu.utils.ContextLogBean;
import com.conmu.utils.WebLoggingBean;
import com.conmu.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yine on 2016/12/29.
 */
@Slf4j
@Aspect
@Component
public class RequestAspect<T> {

//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
//            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
//            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
//            "@annotation(org.springframework.web.bind.annotation.PutMapping)")
//指定包下所有方法
    @Pointcut("execution(public * com.conmu.controller.innerapi..BookController.test(..))")
    private void pointcutRequestMappingMethod() {}

    @Around("pointcutRequestMappingMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long begintime = System.currentTimeMillis();
        try {
            ContextLogBean.before();
            //获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String uri = request.getRequestURI();
            String userip = getRequestIp(request);
            ContextLogBean.setPath(uri);
            ContextLogBean.setIp(userip);
//            ContextLogBean.setParams(request.getParameterMap());
            Map<String, Object> requestParam = getRequestParam(point);    // 获取请求参数
            ContextLogBean.setProps(requestParam);
            Object object = point.proceed();
            ContextLogBean.setResponse(object);
            return object;
        } catch (Exception e) {
            return null;
        } finally {
            long endtime = System.currentTimeMillis();
            ContextLogBean.setSpendtime(endtime - begintime);
            ContextLogBean.log();
        }
    }

    private HttpServletRequest getHttpServerRequest(Object[] args) {
        HttpServletRequest request = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }
        return request;
    }

    private static String getRequestIp(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        return ip;
    }

    // 获取请求参数
    public Map<String, Object> getRequestParam(ProceedingJoinPoint joinPoint) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }
        } catch (Exception e) {
        }
        return paramMap;
    }

    /**
     * 当有业务异常抛出时，适配国际化
     *
     * @return
     */
    private String getReqLanguage() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String language = request.getHeader("Accept-Language");
        return language;
    }

}
