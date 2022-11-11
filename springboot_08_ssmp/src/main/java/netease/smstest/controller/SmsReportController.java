package netease.smstest.controller;

import com.alibaba.fastjson.JSONObject;
import netease.smstest.service.*;
import netease.smstest.util.AppException;
import netease.smstest.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 状态码 接口文档： https://docs.popo.netease.com/lingxi/33b96ac100d3475ebc096d6676d3ef35#edit
 *
 * @author mucongcong
 * @date 2022/10/10 14:20
 * @since
 **/
@Controller
@RequestMapping("/report")
public class SmsReportController {

    @Autowired
    ChannelProviderRelationshipService channelProviderRelationshipService;

    @Autowired
    ChannelProviderService channelProviderService;

    @Autowired
    NimSmsPersonalReportService nimSmsPersonalReportService;

    @Autowired
    NimSmsProviderReportService nimSmsProviderReportService;

    @Autowired
    NimSmsReportService nimSmsReportService;


    /**
     * @Description 通道商新增
     * 
     * @Param [flag, providerId, providerName, description]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider"}, method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> addProvider(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderService.add(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商删除
     *
     * @Param [flag, providerId]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider"}, method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deleteProvider(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderService.delete(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商修改
     *
     * @Param [flag, providerId, providerName, description]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updateProvider(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderService.update(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商查询
     *
     * @Param [flag, providerId, providerName]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider/get"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getProvider(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderService.get(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商-通道关系新增
     *
     * @Param [providerId, channelid]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider/relationship"}, method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> addChannelRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderRelationshipService.add(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商-通道关系删除
     *
     * @Param [channelid]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider/relationship"}, method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deleteChannelRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderRelationshipService.delete(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商-通道关系修改 (考虑废弃掉，用不上)
     *
     * @Param [providerId, channelid]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider/relationship"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updateChannelRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderRelationshipService.update(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 通道商-通道关系查询
     *
     * @Param [providerId, channelid]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/provider/relationship/get"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getChannelRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = channelProviderRelationshipService.get(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 云信规约错误码新增
     *
     * @Param [nimReport, nimReason]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/nim"}, method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> addNimReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsReportService.add(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 云信规约错误码删除
     *
     * @Param [nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/nim"}, method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deleteNimReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsReportService.delete(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 云信规约错误码修改
     *
     * @Param [nimReport, nimReason]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/nim"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updateNimReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsReportService.update(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 云信规约错误码查询
     *
     * @Param [nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/nim/get"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getNimReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsReportService.get(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 回执码对应关系新增
     *
     * @Param [flag, providerId, report, reason, nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/relationship"}, method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> addReportRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsProviderReportService.add(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 回执码对应关系删除
     *
     * @Param [flag, providerId, report]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/relationship"}, method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deleteReportRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsProviderReportService.delete(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 回执码对应关系修改
     *
     * @Param [flag, providerId, report, reason, nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/relationship"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updateReportRelationship(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsProviderReportService.update(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 回执码对应关系查询-通道商错误码
     * 
     * @Param [flag, providerId]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/relationship/getall"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getReportRelationshipAll(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsProviderReportService.getAll(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 回执码对应关系查询-通道回执码情况
     * 
     * @Param [report, channelid]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/relationship/getchannel"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getReportRelationshipChannel(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsProviderReportService.get(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 用户个性化错误码新增
     * 
     * @Param [appid, nimReport, personalReport, personalReason]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/personal"}, method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> addPersonalReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsPersonalReportService.add(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 用户个性化错误码修改
     * 
     * @Param [appid, nimReport, personalReport, personalReason]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/personal"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> updatePersonalReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsPersonalReportService.update(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 用户个性化错误码删除
     * 
     * @Param [appid, nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/personal"}, method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> deletePersonalReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsPersonalReportService.delete(request);
        return ResponseUtil.genSuccessResponse(result);
    }

    /**
     * @Description 用户个性化错误码查询
     * 
     * @Param [appid, nimReport]
     * @return org.springframework.http.ResponseEntity<com.alibaba.fastjson.JSONObject>
     **/
    @RequestMapping(value = {"/personal/get"}, method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getPersonalReport(HttpServletRequest request) throws AppException {
        JSONObject result = nimSmsPersonalReportService.get(request);
        return ResponseUtil.genSuccessResponse(result);
    }
}
