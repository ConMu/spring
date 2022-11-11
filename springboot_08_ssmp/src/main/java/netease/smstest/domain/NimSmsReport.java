package netease.smstest.domain;

/**
 * 云信错误码
 *
 * @author mucongcong
 * @date 2022/10/10 14:09
 * @since
 **/
public class NimSmsReport {
    private Long id;

    /**
     * 云信错误码
     **/
    private String nimReport;

    /**
     * 云信错误原因
     **/
    private String nimReason;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimReport() {
        return nimReport;
    }

    public void setNimReport(String nimReport) {
        this.nimReport = nimReport;
    }

    public String getNimReason() {
        return nimReason;
    }

    public void setNimReason(String nimReason) {
        this.nimReason = nimReason;
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
