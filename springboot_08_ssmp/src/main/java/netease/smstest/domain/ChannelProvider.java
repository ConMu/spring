package netease.smstest.domain;

/**
 * 通道商/区域
 *
 * @author mucongcong
 * @date 2022/10/10 14:03
 * @since
 **/
public class ChannelProvider {

    /**
     * 通道商id/区域码
     **/
    private Long providerId;

    /**
     * 通道商/区域名称
     **/
    private String providerName;

    /**
     * 通道商/区域说明
     **/
    private String description;

    private Long createTime;

    private Long updateTime;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
