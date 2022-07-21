package netease.rcstest.sdkparams;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2022/2/11 16:30
 */
public class RespBodyRcsBase {
    private Integer httpCode;
    private String httpMessage;
    private Integer errorCode;
    private String errorMessage;

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
