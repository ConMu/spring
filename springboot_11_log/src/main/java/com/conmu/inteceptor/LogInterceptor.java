package com.conmu.inteceptor;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.conmu.enums.RespCodeEnums;
import com.conmu.utils.ContextLogBean;
import com.conmu.utils.GsonUtils;
import com.conmu.utils.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 *
 * @Author: zxl
 * @Date: 2020/9/18 15:12
 * @Email: zxl@xxx.com
 * @Description:
 **/
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory
            .getLogger(LogInterceptor.class);

    private static final String loginBean = "logBean";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        ContextLogBean.before();
        ContextLogBean.setPath(request.getRequestURL().toString());
        ContextLogBean.setParams(request.getParameterMap());
        ContextLogBean.setIp(Request.getRequestIp(request));
        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
        String para = new String(bodyBytes);
//        if (true) {
//            //判断是否存在不合法的字符集
//            for (char c : para.toCharArray()) {
//                if (Character.isSurrogate(c)) {
//                    writeAuthFailMsg(response, RespCodeEnums.PARAM_ERROR.getCode(), "不合法的字符");
//                    return false;
//                }
//            }
//        }
        if (true) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(para);
                ContextLogBean.getProps().put("requestBody", jsonObject.toJSONString());
            } catch (Exception e) {
                ContextLogBean.getProps().put("requestBody", para);
            }
        }
        return true;
    }

    private void writeAuthFailMsg(HttpServletResponse response, Integer code, String message) {
        Map<String, Object> resp = new HashMap<>(16);
        resp.put("code", code);
        resp.put("message", message);
        resp.put("success", false);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(GsonUtils.toJsonString(resp));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        ContextLogBean.after();
    }
}
