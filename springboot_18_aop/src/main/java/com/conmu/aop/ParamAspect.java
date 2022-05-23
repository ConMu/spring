package com.conmu.aop;

import com.conmu.po.User;
import com.conmu.response.ResultBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author mucongcong
 * @date 2022/05/23 14:33
 * @since
 **/

@Aspect
@Component
public class ParamAspect {
    @Pointcut("execution(public * com.conmu.controller.*.*(..))")
    public void doOperation(){}

    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            User user =(User) obj;
            System.out.println("前置通知接受的参数:"+user);
            String name =base64DeStr(user.getName());
            user.setName(name);
        }
    }

    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        ResultBody resultBody = (ResultBody) object;
        String str =null;
        try {
            str=base64EnStr(resultBody.getResult());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resultBody.setResult(str);
        System.out.println("前置通知响应的参数:"+resultBody);
    }


    public  String base64EnStr(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }


    public static String base64DeStr(String encodeStr) throws UnsupportedEncodingException {
        byte[] decodeStr = Base64.getDecoder().decode(encodeStr);
        return new String(decodeStr, "UTF-8");
    }

}
