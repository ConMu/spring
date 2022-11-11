package com.conmu.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * WEB服务响应通用错误码定义表
 * @author yine
 * @createTime 2021/4/20 3:20 下午
 * @description
 */
public enum RespCodeEnums {
    /**
     * public [0,600)
     */
    // 成功
    SUCCESS(200, "SUCCESS"),
    ERR_BAN(301, "ERR_BAN"),// 验证失败，且被封禁
    ERR_IP_ACCESS(315, "IP LIMIT"),
    PARAM_ERROR(400, "PARAM_ERROR"),
    AUTH_ERROR(401, "AUTH_ERROR"),
    ERR_FORBIDDEN(403, "ERR_FORBIDDEN"), // 禁止使用
    ERR_NOT_FOUND(404, "ERR_NOT_FOUND"),
    ERR_VERIFY(413, "ERR_VERIFY"), // 验证失败
    ERR_PARAM(414, "ERR_PARAM"), // 参数错误
    ERR_FREQUENT_LIMIT(416, "ERR_FREQUENT_LIMIT"), // 频率限制
    INTERNAL_ERROR(500, "INTERNAL_ERROR"),
    ;

    /**
     * business [600,1000)
     */

    ;

    //=======================================esls event end====================================

    private int code;
    private String message;

    RespCodeEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private static final Map<Integer, RespCodeEnums> lookup = new HashMap<>();

    static {
        for (RespCodeEnums o : EnumSet.allOf(RespCodeEnums.class)) {
            lookup.put(o.getCode(), o);
        }
    }

    public static RespCodeEnums lookup(int code) {
        return lookup.get(code);
    }
}
