package netease.rcstest.ao;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2021/12/16 10:58
 */
public class CspRespBody {
    private int httpCode;
    private String httpMessage;
    private String data;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CspRespBody{" +
                "httpCode=" + httpCode +
                ", httpMessage='" + httpMessage + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
