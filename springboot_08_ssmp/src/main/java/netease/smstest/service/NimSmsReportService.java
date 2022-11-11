package netease.smstest.service;

import com.alibaba.fastjson.JSONObject;

import netease.smstest.dao.NimSmsPersonalReportDao;
import netease.smstest.dao.NimSmsProviderReportDao;
import netease.smstest.dao.NimSmsReportDao;
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
 * 云信错误码service
 *
 * @author mucongcong
 * @date 2022/10/10 15:29
 * @since
 **/
@Service
public class NimSmsReportService {

    @Autowired
    NimSmsReportDao nimSmsReportDao;

    @Autowired
    NimSmsProviderReportDao nimSmsProviderReportDao;

    @Autowired
    NimSmsPersonalReportDao nimSmsPersonalReportDao;

    private static final Logger logger = LoggerFactory.getLogger(NimSmsReportService.class);

    public JSONObject add(HttpServletRequest request) throws AppException {
        String nimReport = request.getParameter("nimReport");
        String nimReason = request.getParameter("nimReason");
        BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
        BaseCheckUtil.checkParamIsEmpty(nimReason, "nimReason");
        if (nimSmsReportDao.getByNimReport(nimReport) != null) {
            throw new AppException(ResponseCode.RES_EPARAM, "nimReport already exit");
        }

        NimSmsReport nimSmsReport = new NimSmsReport();
        nimSmsReport.setNimReason(nimReason);
        nimSmsReport.setNimReport(nimReport);
        long nowTime = System.currentTimeMillis();
        nimSmsReport.setCreateTime(nowTime);
        nimSmsReport.setUpdateTime(nowTime);
        nimSmsReportDao.add(nimSmsReport);

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject delete(HttpServletRequest request) throws AppException {
        String nimReport = request.getParameter("nimReport");
        BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
        if (!CollectionUtils.isEmpty(nimSmsProviderReportDao.getByNimReport(nimReport, 20, 0))) {
            throw new AppException(ResponseCode.RES_FORBIDDEN, "binding relationship provider report exists");
        }
        if (!CollectionUtils.isEmpty(nimSmsPersonalReportDao.getByNimReport(nimReport, 20, 0))) {
            throw new AppException(ResponseCode.RES_FORBIDDEN, "binding relationship personal report exists");
        }
        nimSmsReportDao.delete(request.getParameter("nimReport"));

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject update(HttpServletRequest request) throws AppException {
        String nimReport = request.getParameter("nimReport");
        String nimReason = request.getParameter("nimReason");
        BaseCheckUtil.checkParamIsEmpty(nimReport, "nimReport");
        BaseCheckUtil.checkParamIsEmpty(nimReason, "nimReason");
        if (nimSmsReportDao.getByNimReport(nimReport) == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "nimReport not exit");
        }

        NimSmsReport nimSmsReport = new NimSmsReport();
        nimSmsReport.setNimReport(nimReport);
        nimSmsReport.setNimReason(nimReason);
        long nowTime = System.currentTimeMillis();
        nimSmsReport.setUpdateTime(nowTime);
        nimSmsReportDao.update(nimSmsReport);

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject get(HttpServletRequest request) throws AppException {
        String nimReport = request.getParameter("nimReport");
        int limit = BaseCheckUtil.checkIntegerParam(request.getParameter("limit"), "limit");
        int offset = BaseCheckUtil.checkIntegerParam(request.getParameter("offset"), "offset");
        ReportCheckUtil.pageCheck(limit, offset);

        List<NimSmsReport> data;
        if (StringUtils.isEmpty(nimReport)) {
            data = nimSmsReportDao.getAll(limit, offset);
        } else {
            data = new ArrayList<>();
            data.add(nimSmsReportDao.getByNimReport(nimReport));
        }
        JSONObject result = new JSONObject();
        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }
}
