package netease.rcstest.sdkparams;


/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2022/2/11 12:04
 */
public class RcsAccessTokenUnicom5GVO {
    private String appId;
    private String appKey;

    public RcsAccessTokenUnicom5GVO() {
    }

    public RcsAccessTokenUnicom5GVO(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}

