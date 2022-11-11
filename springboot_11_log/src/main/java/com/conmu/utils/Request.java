package com.conmu.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by yine on 2016/12/30.
 */
public class Request {
    private static final Logger logger = LoggerFactory.getLogger(Request.class);
    private static final String ipDigitPattern = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)";
    private static final Pattern internalIpPattern = Pattern
            .compile(String.format("(10(\\.%s){3})|(172\\.(1[6-9]|2\\d|3[01])(\\.%s){2})|(192\\.168(\\.%s){2})|(127\\.0\\.0\\.%s)",
                    ipDigitPattern, ipDigitPattern, ipDigitPattern, ipDigitPattern));

    /**
     * 获取请求IP
     *
     * @param request
     * @param useInternalIp
     * @return
     */
    public static String getRequestIp(HttpServletRequest request, boolean useInternalIp) {
        try {
            String header1 = request.getHeader("X-Forwarded-For");
            String header2 = request.getHeader("Proxy-Client-IP");
            String header3 = request.getHeader("WL-Proxy-Client-IP");

            String header = null;
            if (StringUtils.isNotBlank(header1) && !"unknown".equalsIgnoreCase(header1)) {
                header = header1;
            } else if (StringUtils.isNotBlank(header2) && !"unknown".equalsIgnoreCase(header2)) {
                header = header2;
            } else if (StringUtils.isNotBlank(header3) && !"unknown".equalsIgnoreCase(header3)) {
                header = header3;
            }

            String realIp = null;
            if (StringUtils.isNotBlank(header)) {
                String[] ips = header.split(",");
                for (String ip : ips) {
                    // 过滤2g/3g网关添加的内网ip
                    if (!isInternalIp(ip)) {
                        realIp = ip;
                        break;
                    }
                }

                // 只有内网ip并且应用允许的情况下才取内网ip
                if (realIp == null && useInternalIp) {
                    realIp = ips[0];
                }
            }

            if (StringUtils.isBlank(realIp)) {
                realIp = request.getRemoteAddr();
            }

            return realIp.trim();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 获取请求IP(过滤内网IP)
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        return getRequestIp(request, false);
    }

    public static boolean isInternalIp(String str) {
        return internalIpPattern.matcher(str).matches();
    }

    public static Map<String, List<String>> getRequestHeaders(HttpServletRequest request) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            ArrayList<String> value = Collections.list(request.getHeaders(key));
            map.put(key, value);
        }

        return map;
    }
}
