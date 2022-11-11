package netease.smstest.domain;

/**
 * 通道商/区域-错误码
 *
 * @author mucongcong
 * @date 2022/10/10 14:11
 * @since
 **/
public class NimSmsProviderReport {
    private Long id;

    /**
     * 通道商id/区域码
     **/
    private Long providerId;

    /**
     * 运营商错误码
     **/
    private String report;

    /**
     * 运营商错误原因
     **/
    private String reason;

    /**
     * 对应的云信错误码
     **/
    private String nimReport;

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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNimReport() {
        return nimReport;
    }

    public void setNimReport(String nimReport) {
        this.nimReport = nimReport;
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
