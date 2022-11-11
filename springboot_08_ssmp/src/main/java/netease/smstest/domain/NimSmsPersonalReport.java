package netease.smstest.domain;

/**
 * 客户个性化错误码
 *
 * @author mucongcong
 * @date 2022/10/10 14:14
 * @since
 **/
public class NimSmsPersonalReport {
    private Long id;

    private Long appId;

    /**
     * 云信错误码
     **/
    private String nimReport;

    /**
     * 用户自定义的错误码
     **/
    private String personalReport;

    /**
     * 用户自定义的错误原因
     **/
    private String personalReason;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getNimReport() {
        return nimReport;
    }

    public void setNimReport(String nimReport) {
        this.nimReport = nimReport;
    }

    public String getPersonalReport() {
        return personalReport;
    }

    public void setPersonalReport(String personalReport) {
        this.personalReport = personalReport;
    }

    public String getPersonalReason() {
        return personalReason;
    }

    public void setPersonalReason(String personalReason) {
        this.personalReason = personalReason;
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
