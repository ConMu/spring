package netease.rcstest.sdkparams;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2022/2/11 11:47
 */
public class RespBodyRcsAccessToken extends RespBodyRcsBase {
    private String httpMessage;
    private String accessToken;
    private Integer expires;
    private String url;



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
