package netease.smstest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import netease.smstest.dao.NimSmsPersonalReportDao;
import netease.smstest.dao.NimSmsProviderReportDao;
import netease.smstest.dao.NimSmsReportDao;
import netease.smstest.domain.NimSmsPersonalReport;
import netease.smstest.domain.NimSmsProviderReport;
import netease.smstest.domain.NimSmsReport;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 用户个性化错误码service
 *
 * @author mucongcong
 * @date 2022/10/10 15:36
 * @since
 **/
@Service
public class NimSmsPersonalReportService {

    private static final Logger logger = LoggerFactory.getLogger(NimSmsPersonalReportService.class);

    @Autowired
    NimSmsPersonalReportDao nimSmsPersonalReportDao;

    @Autowired
    NimSmsProviderReportDao nimSmsProviderReportDao;

    @Autowired
    NimSmsReportDao nimSmsReportDao;

    public JSONObject add(HttpServletRequest request) throws AppException {
        long appid = BaseCheckUtil.checkLongParam(request.getParameter("appid"), "appid");
        String reports = request.getParameter("reports");
        if (StringUtils.isEmpty(reports)) {
            throw new AppException(ResponseCode.RES_EPARAM, "reports is empty");
        }
        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(reports, "reports");
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "channelids format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            String nimReport = jsonObject.getString("nimReport");
            String personalReport = jsonObject.getString("personalReport");
            String personalReason = jsonObject.getString("personalReason");
            BaseCheckUtil.checkParamIsEmpty(nimReport,"nimReport");
            BaseCheckUtil.checkParamIsEmpty(personalReport,"personalReport");
            // 规定的状态码粒度更小，与个性化的状态码关系为 一对多，校验
            NimSmsReport nimReportCheck = nimSmsReportDao.getByNimReport(nimReport);
            if (nimReportCheck == null) {
                throw new AppException(ResponseCode.RES_EPARAM, "illegal nimReport" + nimReport);
            }
            if (nimSmsPersonalReportDao.getByAppidAndNimReport(appid, nimReport) != null) {
                throw new AppException(ResponseCode.RES_EPARAM, "nimReport already exit");
            }
            if (StringUtils.isEmpty(personalReason)) {
                personalReason = nimReportCheck.getNimReason();
            }

            NimSmsPersonalReport nimSmsPersonalReport = new NimSmsPersonalReport();
            nimSmsPersonalReport.setAppId(appid);
            nimSmsPersonalReport.setPersonalReport(personalReport);
            nimSmsPersonalReport.setPersonalReason(personalReason);
            nimSmsPersonalReport.setNimReport(nimReport);
            long nowTime = System.currentTimeMillis();
            nimSmsPersonalReport.setCreateTime(nowTime);
            nimSmsPersonalReport.setUpdateTime(nowTime);

            nimSmsPersonalReportDao.add(nimSmsPersonalReport);
        }

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject update(HttpServletRequest request) throws AppException {
        long appid = BaseCheckUtil.checkLongParam(request.getParameter("appid"), "appid");
        String nimReport = request.getParameter("nimReport");
        String personalReport = request.getParameter("personalReport");
        String personalReason = request.getParameter("personalReason");
        BaseCheckUtil.checkParamIsEmpty(nimReport,"nimReport");
        BaseCheckUtil.checkParamIsEmpty(personalReport,"personalReport");
        // 规定的状态码粒度更小，与个性化的状态码关系为 一对多，校验
        NimSmsReport nimReportCheck = nimSmsReportDao.getByNimReport(nimReport);
        if (nimReportCheck == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "illegal nimReport" + nimReport);
        }
        if (nimSmsPersonalReportDao.getByAppidAndNimReport(appid, nimReport) != null) {
            throw new AppException(ResponseCode.RES_EPARAM, "nimReport already exit");
        }
        if (StringUtils.isEmpty(personalReason)) {
            personalReason = nimReportCheck.getNimReason();
        }

        NimSmsPersonalReport nimSmsPersonalReport = new NimSmsPersonalReport();
        nimSmsPersonalReport.setAppId(appid);
        nimSmsPersonalReport.setPersonalReport(personalReport);
        nimSmsPersonalReport.setPersonalReason(personalReason);
        nimSmsPersonalReport.setNimReport(nimReport);
        long nowTime = System.currentTimeMillis();
        nimSmsPersonalReport.setUpdateTime(nowTime);
        nimSmsPersonalReportDao.update(nimSmsPersonalReport);

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject delete(HttpServletRequest request) throws AppException {
        long appid = BaseCheckUtil.checkLongParam(request.getParameter("appid"), "appid");
        String reports = request.getParameter("reports");
        BaseCheckUtil.checkParamIsEmpty(reports, "reports");

        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(reports, "reports");
        List<String> nimReportList = new ArrayList<>();
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "reports format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            String nimReport = jsonObject.getString("nimReport");
            BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
            nimReportList.add(nimReport);
        }
        for (String deleteNimReport : nimReportList) {
            nimSmsPersonalReportDao.delete(appid, deleteNimReport);
        }

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject get(HttpServletRequest request) throws AppException {
        long appid = BaseCheckUtil.checkLongParam(request.getParameter("appid"), "appid");
        String nimReport = request.getParameter("nimReport");
        int limit = BaseCheckUtil.checkIntegerParam(request.getParameter("limit"), "limit");
        int offset = BaseCheckUtil.checkIntegerParam(request.getParameter("offset"), "offset");
        ReportCheckUtil.pageCheck(limit, offset);

        JSONObject result = new JSONObject();
        List<NimSmsPersonalReport> data;
        if (StringUtils.isEmpty(nimReport)) {
            data = nimSmsPersonalReportDao.getAll(appid,limit,offset);
        } else {
            data = new ArrayList<>();
            data.add(nimSmsPersonalReportDao.getByAppidAndNimReport(appid, nimReport));
        }

        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }
}
