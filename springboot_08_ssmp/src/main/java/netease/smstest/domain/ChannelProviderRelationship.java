package netease.smstest.domain;

/**
 * 通道商-通道对应关系
 *
 * @author mucongcong
 * @date 2022/10/10 14:07
 * @since
 **/
public class ChannelProviderRelationship {
    private Long id;

    /**
     * 通道商id/区域码
     **/
    private Long providerId;

    /**
     * 通道Id
     **/
    private Long channelId;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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
