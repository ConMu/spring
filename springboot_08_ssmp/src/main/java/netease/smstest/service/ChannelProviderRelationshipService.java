package netease.smstest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import netease.smstest.dao.ChannelProviderDao;
import netease.smstest.dao.ChannelProviderRelationshipDao;
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
import java.util.List;

/**
 * 通道商-通道关系service
 *
 * @author mucongcong
 * @date 2022/10/10 15:29
 * @since
 **/
@Service
public class ChannelProviderRelationshipService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelProviderRelationshipService.class);

    @Autowired
    ChannelProviderRelationshipDao channelProviderRelationshipDao;

    @Autowired
    ChannelProviderDao channelProviderDao;

    public JSONObject add(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        String channelids = request.getParameter("channelids");
        if (StringUtils.isEmpty(channelids)) {
            throw new AppException(ResponseCode.RES_EPARAM, "channelids is empty");
        }
        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(channelids, "channelids");
        List<Long> channelidList = new ArrayList<>();
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "channelids format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            channelidList.add(BaseCheckUtil.checkLongParam(jsonObject.getString("channelid"),"channelid"));
        }

        for (Long channelid : channelidList) {
            if (channelProviderRelationshipDao.getByChannelId(channelid) != null) {
                throw new AppException(ResponseCode.RES_EPARAM, "channelid: " + channelid + " already exit");
            }
        }
        for (Long channelid : channelidList) {
            ChannelProviderRelationship channelProviderRelationship = new ChannelProviderRelationship();
            channelProviderRelationship.setChannelId(channelid);
            channelProviderRelationship.setProviderId(providerId);
            long nowTime = System.currentTimeMillis();
            channelProviderRelationship.setCreateTime(nowTime);
            channelProviderRelationship.setUpdateTime(nowTime);
            channelProviderRelationshipDao.add(channelProviderRelationship);
        }

        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject delete(HttpServletRequest request) throws AppException {
        String channelids = request.getParameter("channelids");
        if (StringUtils.isEmpty(channelids)) {
            throw new AppException(ResponseCode.RES_EPARAM, "channelids is illegal");
        }
        JSONArray jsonArray = BaseCheckUtil.checkJsonArrayParam(channelids, "channelids");
        List<Long> channelidList = new ArrayList<>();
        for (Object o : jsonArray) {
            if (!(o instanceof JSONObject)) {
                throw new AppException(ResponseCode.RES_EPARAM, "channelids format error");
            }
            JSONObject jsonObject = (JSONObject) o;
            channelidList.add(BaseCheckUtil.checkLongParam(jsonObject.getString("channelid"),"channelid"));
        }
        for (Long deleteChannelid : channelidList) {
            channelProviderRelationshipDao.delete(deleteChannelid);
        }
        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject update(HttpServletRequest request) throws AppException {
        long providerId = BaseCheckUtil.checkLongParam(request.getParameter("providerId"), "providerId");
        long channelid = BaseCheckUtil.checkLongParam(request.getParameter("channelid"), "channelid");
        if (channelProviderDao.getByProviderId(providerId) == null) {
            throw new AppException(ResponseCode.RES_EPARAM, "providerId is not exist");
        }
        if (channelProviderRelationshipDao.getByChannelId(channelid) != null) {
            throw new AppException(ResponseCode.RES_EPARAM, "channelid is not exist");
        }

        ChannelProviderRelationship channelProviderRelationship = new ChannelProviderRelationship();
        channelProviderRelationship.setChannelId(channelid);
        channelProviderRelationship.setProviderId(providerId);
        long nowTime = System.currentTimeMillis();
        channelProviderRelationship.setUpdateTime(nowTime);
        channelProviderRelationshipDao.update(channelProviderRelationship);
        JSONObject result = new JSONObject();
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }

    public JSONObject get(HttpServletRequest request) throws AppException {
        String providerId = request.getParameter("providerId");
        String channelid = request.getParameter("channelid");
        int limit = BaseCheckUtil.checkIntegerParam(request.getParameter("limit"), "limit");
        int offset = BaseCheckUtil.checkIntegerParam(request.getParameter("offset"), "offset");
        ReportCheckUtil.pageCheck(limit, offset);

        JSONObject result = new JSONObject();
        List<ChannelProviderRelationship> data;
        if (StringUtils.isEmpty(providerId) && StringUtils.isEmpty(channelid)) {
            data = channelProviderRelationshipDao.getAll(limit,offset);
        } else if (StringUtils.isNotEmpty(providerId) ) {
            long l = BaseCheckUtil.checkLongParam(providerId, "providerId");
            data = channelProviderRelationshipDao.getByProviderId(l);
        } else {
            long s = BaseCheckUtil.checkLongParam(request.getParameter("channelid"), "channelid");
            data = new ArrayList<>();
            data.add(channelProviderRelationshipDao.getByChannelId(s));
        }

        result.put("data", data);
        result.put(ResponseCode.CODE, ResponseCode.RES_SUCCESS);
        return result;
    }
}
