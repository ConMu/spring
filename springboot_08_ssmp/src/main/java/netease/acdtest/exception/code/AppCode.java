package netease.acdtest.exception.code;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * WEB服务响应通用错误码定义表
 */
public enum AppCode {

    /**
     * 成功
     */
    OK(0, "OK"),

    /**
     * 失败
     */
    INTERNAL_ERROR(500, "INTERNAL_ERROR"),

    /**
     * 未知错误
     */
    UNKNOWN(800, "UNKNOWN"),

    /**
     * 没有权限
     */
    NO_AUTH(801, "NO_AUTH"),

    /**
     * 数据库错误
     */
    DB_ERROR(802, "DB_ERROR"),

    /**
     * 非法参数
     */
    BAD_REQUEST(803, "BAD_REQUEST"),

    /**
     * 权限模块
     */
    APPID_NO_PERMISSION(403, "用户无权限"),

    /**
     * 权限添加失败
     */
    USER_ADD_PERMISSION_FAIL(11, "用户权限添加失败"),

    /**
     * 权限添加失败
     */
    ADD_PERMISSION_USER_FAIL(12, "添加用户权限失败用户权限添加失败");

    private int code;
    private String message;

    AppCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private static final Map<Integer, AppCode> lookup = new HashMap<>();

    static {
        for (AppCode o : EnumSet.allOf(AppCode.class)) {
            lookup.put(o.getCode(), o);
        }
    }

    public static AppCode lookup(int code) {
        return lookup.get(code);
    }
}
