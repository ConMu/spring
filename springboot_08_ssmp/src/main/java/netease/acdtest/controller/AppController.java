package netease.acdtest.controller;



import lombok.extern.slf4j.Slf4j;
import netease.acdtest.config.ACDConfig;
import netease.acdtest.param.ao.AppAo;
import netease.acdtest.param.bo.AppBo;
import netease.acdtest.servicelocal.AppServiceLocal;
import netease.acdtest.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用controller
 */

@Slf4j
@Controller
@RequestMapping(ACDConfig.ACD_URL_PREFIX)
public class AppController {

    @Autowired
    AppServiceLocal appService;

    /**
     * @Description 添加应用
     *
     * @Param [appAo]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @RequestMapping(value = "/apps",method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> createApp(@RequestBody AppAo appAo) {
        appService.createApp(appAo);
        return Response.success();
    }

    /**
     * @Description 编辑应用
     *
     * @Param [appAo]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @RequestMapping(value = "/apps",method = RequestMethod.PUT)
    @ResponseBody
    public Response<Void> editApp( @RequestBody AppAo appAo) {
        appService.editApp(appAo);
        return Response.success();
    }

    /**
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     * @Description 根据appId删除应用
     * @Param [appId]
     **/
    @RequestMapping(value = "/apps/id/{appId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Void> deleteAppByAppId(@PathVariable String appId) {
        appService.deleteAppByAppId(appId);
        return Response.success();
    }

    /**
     * @Description 分页查询所有应用
     *
     * @Param [limit, offset, appId]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.util.List<com.netease.we.voip.bs.acd.param.bo.AppBo>>
     **/
    @RequestMapping(value = "/apps/list",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<AppBo>> getAppList(@RequestParam(required = false) Integer limit,
                                            @RequestParam(required = false) Integer offset) {
        List<AppBo> appBos = appService.getAppListPage(limit, offset, null);
        return Response.success(appBos);
    }

    /**
     * @Description 根据appId搜索查询单个应用
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.util.List<com.netease.we.voip.bs.acd.param.bo.AppBo>>
     **/
    @RequestMapping(value = "/apps/id/{appId}",method = RequestMethod.GET)
    @ResponseBody
    public Response<List<AppBo>> getAppByAppId (@PathVariable String appId) {
        List<AppBo> appBos = appService.getAppListPage(null, null, appId);
        return Response.success(appBos);
    }

}
