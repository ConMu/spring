package netease.rcstest.execption;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2021/12/16 10:50
 */
public class CspHttpRequestException extends RuntimeException {
    private int code;
    private String msg;

    public CspHttpRequestException(Throwable cause) {
        super(cause);
    }

    public CspHttpRequestException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public CspHttpRequestException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
