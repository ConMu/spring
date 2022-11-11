package netease.smstest.service;

import com.alibaba.fastjson.JSONObject;

import netease.smstest.dao.ChannelProviderDao;
import netease.smstest.dao.ChannelProviderRelationshipDao;
import netease.smstest.dao.NimSmsProviderReportDao;
import netease.smstest.domain.ChannelProvider;
import netease.smstest.domain.ChannelProviderRelationship;
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
import java.util.Arrays;
import java.util.List;

/**
 * 通道商service
 *
 * @author mucongcong
 * @date 2022/10/10 15:28
 * @since
 **/
@Service
public class ChannelProviderService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelProviderService.class);

    @Autowired
    ChannelProviderDao channelProviderDao;

    @Autowired
    ChannelProviderRelationshipDao channelProviderRelationshipDao;

    @Autowired
    NimSmsProviderReportDao nimSmsProviderReportDao;

    public JSONObject add(HttpServletRequest request) throws AppException {
        String providerName = request.getParameter("providerName");
        String description = request.getParameter("description");
        BaseCheckUtil.checkParamIsEmpty(providerName,"providerName");

        JSONObject result = new JSONObject();

        ChannelProvider channelProvider = new ChannelProvider();
        channelProvider.setProviderName(providerName);
        channelProvider.setDescription(description);
        long nowTime = System.currentTimeMillis();
        channelProvider.setCreateTime(nowTime);
        channelProvider.setUpdateTime(nowTime);

        channelProviderDao.add(channelProvider);

        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject delete(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        List<ChannelProviderRelationship> relationships = channelProviderRelationshipDao.getByProviderId(providerId);
        if (!CollectionUtils.isEmpty(relationships)) {
            throw new AppException(ResponseCode.RES_FORBIDDEN, "binding relationship channel: " + relationships.get(0).getChannelId() + "exists");
        }

        if (!CollectionUtils.isEmpty(nimSmsProviderReportDao.getAllByProviderId(providerId, 20, 0))) {
            throw new AppException(ResponseCode.RES_FORBIDDEN, "binding relationship report exists");
        }

        JSONObject result = new JSONObject();
        channelProviderDao.delete(providerId);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject update(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        String providerName = request.getParameter("providerName");
        String description = request.getParameter("description");
        BaseCheckUtil.checkParamIsEmpty(providerName,"providerName");

        JSONObject result = new JSONObject();
        if (channelProviderDao.getByProviderId(providerId) == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "providerId or flag is wrong");
        }

        ChannelProvider channelProvider = new ChannelProvider();
        channelProvider.setProviderId(providerId);
        channelProvider.setProviderName(providerName);
        channelProvider.setDescription(description);
        long nowTime = System.currentTimeMillis();
        channelProvider.setUpdateTime(nowTime);
        channelProviderDao.update(channelProvider);

        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject get(HttpServletRequest request) throws AppException {
        String providerId = request.getParameter("providerId");
        String providerName = request.getParameter("providerName");
        int limit = BaseCheckUtil.checkIntegerParam(request.getParameter("limit"), "limit");
        int offset = BaseCheckUtil.checkIntegerParam(request.getParameter("offset"), "offset");
        ReportCheckUtil.pageCheck(limit, offset);

        JSONObject result = new JSONObject();
        List<ChannelProvider> data;
        if (StringUtils.isEmpty(providerId) && StringUtils.isEmpty(providerName)) {
            data = channelProviderDao.getAll(limit,offset);
        } else if (StringUtils.isNotEmpty(providerId)) {
            long l = BaseCheckUtil.checkLongParam(providerId, "providerId");
            data = new ArrayList<>();
            data.add(channelProviderDao.getByProviderId(l));
        } else {
            data = new ArrayList<>();
            data.add(channelProviderDao.getByProviderName(providerName));
        }
        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }
}
