package netease.acdtest.controller;


import lombok.extern.slf4j.Slf4j;
import netease.acdtest.config.ACDConfig;
import netease.acdtest.param.ao.RouteAo;
import netease.acdtest.param.bo.RouteBo;
import netease.acdtest.servicelocal.RouteServiceLocal;
import netease.acdtest.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 路由相关controller
 *
 * @author mucongcong
 * @date 2022/06/02 09:42
 * @since
 **/
@Slf4j
@Controller
@RequestMapping(ACDConfig.ACD_URL_PREFIX)
public class RouteController {

    @Autowired
    RouteServiceLocal routeService;

    /**
     * @Description 查询指定role的路由  返回k:rootIdentify v路由树
     *
     * @Param [appId, role 客服1，客户2]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<com.netease.we.voip.bs.acd.param.bo.RouteBo>
     **/
    @RequestMapping(value = "/routes/{appId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, RouteBo>> getRoute(@PathVariable String appId,
                                                   @RequestParam(required = false) Integer role,
                                                   @RequestParam(required = false) Integer limit,
                                                   @RequestParam(required = false) Integer offset) {
        Map<String,RouteBo> routes = routeService.getRoute(appId, role,limit,offset);
        return Response.success(routes);
    }

    /**
     * @Description 删除路由
     *
     * @Param [appId, role]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @RequestMapping(value = "/routes/{appId}/{role}/{rootIdentify}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Void> deleteRoute(@PathVariable String appId,
                                      @PathVariable Integer role,
                                      @PathVariable String rootIdentify) {
        routeService.deleteRoute(appId, role, rootIdentify);
        return Response.success();
    }

    /**
     * @Description 添加路由
     *
     * @Param [appId, role, routeAo]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @RequestMapping(value = "/routes",method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> createRoute( @RequestParam String appId,
                                       @RequestParam Integer role,
                                       @RequestParam String routeIdentify,
                                       @RequestBody RouteAo routeAo) {
        // 1. 所有节点加入到route表 2. 根节点加入到app_route表
        routeService.addRoutes(appId,role,routeIdentify,routeAo);
        return Response.success();
    }


    /**
     * @Description 编辑路由
     *
     * @Param [appId, role, routeAo]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @RequestMapping(value = "/routes",method = RequestMethod.PUT)
    @ResponseBody
    public Response<Void> editRoute( @RequestParam String appId,
                                     @RequestParam Integer role,
                                     @RequestParam String rootIdentify,
                                     @RequestBody RouteAo routeAo) {
        // 1. 所有节点加入到route表 2. 根节点加入到app_route表
        routeService.editRoute(appId,role,rootIdentify,routeAo);
        return Response.success();
    }
}
