package netease.smstest.dto;

/**
 * @author mucongcong
 * @date 2022/10/10 20:32
 * @since
 **/
public class NimSmsProviderReportDTO {

    /**
     * 通道id
     **/
    private Long channelid;

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
     * 对应的云信错误原因
     **/
    private String nimReason;

    /**
     * 对应的云信错误码
     **/
    private String nimReport;


    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
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

    public String getNimReason() {
        return nimReason;
    }

    public void setNimReason(String nimReason) {
        this.nimReason = nimReason;
    }

    public String getNimReport() {
        return nimReport;
    }

    public void setNimReport(String nimReport) {
        this.nimReport = nimReport;
    }


}
