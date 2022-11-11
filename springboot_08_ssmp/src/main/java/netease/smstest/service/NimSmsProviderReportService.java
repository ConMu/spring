package netease.smstest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import netease.smstest.dao.ChannelProviderDao;
import netease.smstest.dao.ChannelProviderRelationshipDao;
import netease.smstest.dao.NimSmsProviderReportDao;
import netease.smstest.dao.NimSmsReportDao;
import netease.smstest.domain.ChannelProviderRelationship;
import netease.smstest.domain.NimSmsProviderReport;
import netease.smstest.domain.NimSmsReport;
import netease.smstest.dto.NimSmsProviderReportDTO;
import netease.smstest.util.AppException;
import netease.smstest.util.BaseCheckUtil;
import netease.smstest.util.ReportCheckUtil;
import netease.smstest.util.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 运营商-云信错误码对应关系service
 *
 * @author mucongcong
 * @date 2022/10/10 15:36
 * @since
 **/
@Service
public class NimSmsProviderReportService {

    private static final Logger logger = LoggerFactory.getLogger(NimSmsProviderReportService.class);

    @Autowired
    NimSmsProviderReportDao nimSmsProviderReportDao;

    @Autowired
    ChannelProviderDao channelProviderDao;

    @Autowired
    ChannelProviderRelationshipDao channelProviderRelationshipDao;

    @Autowired
    NimSmsReportDao nimSmsReportDao;

    public JSONObject add(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        String reports = request.getParameter("reports");
        if (StringUtils.isEmpty(reports)) {
            throw new AppException(ResponseCode.RES_EPARAM, "details is empty");
        }
        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(reports, "reports");
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "channelids format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            String report = jsonObject.getString("report");
            String reason = jsonObject.getString("reason");
            String nimReport = jsonObject.getString("nimReport");
            BaseCheckUtil.checkParamIsEmpty(report, "report");
            BaseCheckUtil.checkParamIsEmpty(reason, "reason");
            BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
            if (nimSmsProviderReportDao.getByProviderIdAndReport(providerId, report) != null) {
                throw new AppException(ResponseCode.RES_EPARAM, "report already exist");
            }
            NimSmsReport nimReportCheck = nimSmsReportDao.getByNimReport(nimReport);
            if (nimReportCheck == null) {
                throw new AppException(ResponseCode.RES_EPARAM, "illegal nimReport" + nimReport);
            }

            NimSmsProviderReport nimSmsProviderReport = new NimSmsProviderReport();
            nimSmsProviderReport.setProviderId(providerId);
            nimSmsProviderReport.setNimReport(nimReport);
            nimSmsProviderReport.setReport(report);
            nimSmsProviderReport.setReason(reason);
            long nowTime = System.currentTimeMillis();
            nimSmsProviderReport.setCreateTime(nowTime);
            nimSmsProviderReport.setUpdateTime(nowTime);

            nimSmsProviderReportDao.add(nimSmsProviderReport);
        }

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject delete(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        String reports = request.getParameter("reports");
        if (StringUtils.isEmpty(reports)) {
            throw new AppException(ResponseCode.RES_EPARAM, "reports is empty");
        }
        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(reports, "reports");
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "reports format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            String report = jsonObject.getString("report");
            BaseCheckUtil.checkParamIsEmpty(report, "report");

            nimSmsProviderReportDao.delete( providerId, report);
        }

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject update(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        String report = request.getParameter("report");
        String reason = request.getParameter("reason");
        String nimReport = request.getParameter("nimReport");
        BaseCheckUtil.checkParamIsEmpty(report, "report");
        BaseCheckUtil.checkParamIsEmpty(reason, "reason");
        BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
        if (nimSmsProviderReportDao.getByProviderIdAndReport(providerId, report) == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "providerId has no corresponding report");
        }
        NimSmsReport nimReportCheck = nimSmsReportDao.getByNimReport(nimReport);
        if (nimReportCheck == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "illegal nimReport" + nimReport);
        }

        NimSmsProviderReport nimSmsProviderReport = new NimSmsProviderReport();
        nimSmsProviderReport.setReport(report);
        nimSmsProviderReport.setNimReport(nimReport);
        nimSmsProviderReport.setProviderId(providerId);
        nimSmsProviderReport.setReason(reason);
        long nowTime = System.currentTimeMillis();
        nimSmsProviderReport.setUpdateTime(nowTime);
        nimSmsProviderReportDao.update(nimSmsProviderReport);

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject getAll(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        if (channelProviderDao.getByProviderId(providerId) == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "providerId is wrong");
        }
        int limit = BaseCheckUtil.checkIntegerParam(request.getParameter("limit"), "limit");
        int offset = BaseCheckUtil.checkIntegerParam(request.getParameter("offset"), "offset");
        ReportCheckUtil.pageCheck(limit, offset);

        List<NimSmsProviderReport> data = nimSmsProviderReportDao.getAllByProviderId(providerId, limit, offset);
        JSONObject result = new JSONObject();
        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject get(HttpServletRequest request) throws AppException {
        String report = request.getParameter("report");
        BaseCheckUtil.checkParamIsEmpty(report, "report");
        long channelid = BaseCheckUtil.checkLongParam(request.getParameter("channelid"), "channelid");
        ChannelProviderRelationship relation = channelProviderRelationshipDao.getByChannelId(channelid);
        if (relation == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "channelid has no provider");
        }
        // 根据通道商跟原始的回执码获取云信规定的回执
        NimSmsProviderReport nimSmsProviderReport = nimSmsProviderReportDao.getByProviderIdAndReport( relation.getProviderId(), report);
        if (nimSmsProviderReport == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "this report is not in database");
        }
        // 查询规约对应的原因
        NimSmsReport nimSmsReports = nimSmsReportDao.getByNimReport(nimSmsProviderReport.getNimReport());
        if (nimSmsReports == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "there is no corresponding nimReport");
        }
        NimSmsProviderReportDTO data = new NimSmsProviderReportDTO();
        initDtoData(data, channelid, nimSmsProviderReport, nimSmsReports);
        JSONObject result = new JSONObject();
        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    private void initDtoData(NimSmsProviderReportDTO data, Long channelid, NimSmsProviderReport nimSmsProviderReport, NimSmsReport nimSmsReport) {
        data.setChannelid(channelid);
        data.setNimReport(nimSmsProviderReport.getNimReport());
        data.setNimReason(nimSmsReport.getNimReason());
        data.setReport(nimSmsProviderReport.getReport());
        data.setProviderId(nimSmsProviderReport.getProviderId());
        data.setReason(nimSmsProviderReport.getReason());
    }
}
