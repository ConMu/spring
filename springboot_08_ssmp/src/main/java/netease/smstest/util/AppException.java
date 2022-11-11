package netease.smstest.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

public class AppException extends Exception {

    private int expcode;

    private String desc = "";

    private String msg = null;

    private JSONObject obj = null;

    private ErrorMessageEnum errorMessageEnum;

    public AppException(int code, Throwable cause) {
        super(cause);

        this.expcode = code;
    }

    public int getExpcode() {
        return expcode;
    }

    public String getDesc() {
        return this.desc;
    }

    public JSONObject getObj() {
        return this.obj;
    }

    public AppException(int expcode) {
        super("");
        this.expcode = expcode;
    }

    public AppException(int expcode, String msg) {
        this(expcode, msg, msg);
    }

    public AppException(ErrorMessageEnum errorMessageEnum) {
        this(errorMessageEnum.getCode(), errorMessageEnum.getMessage(), errorMessageEnum.getMessage());
        this.errorMessageEnum = errorMessageEnum;
    }

    public AppException(int expcode, String msg, String desc) {
        super();
        this.msg = msg;
        this.expcode = expcode;
        this.desc = desc;
    }

    public AppException(int expcode, JSONObject obj) {
        this.expcode = expcode;
        this.obj = obj;
    }

    public ErrorMessageEnum getErrorMessageEnum() {
        return errorMessageEnum;
    }

    @Override
    public String toString() {
        JSONObject toString = new JSONObject();
        toString.put("code", expcode);
        if (!StringUtils.isEmpty(msg)) {
            toString.put("msg", msg);
        }
        if (obj != null) {
            toString.put("msg", obj.toJSONString());
        }
        if (!StringUtils.isEmpty(desc)) {
            toString.put("desc", desc);
        }
        toString.put("cause", this.getCause());
        return toString.toJSONString();
    }

}
