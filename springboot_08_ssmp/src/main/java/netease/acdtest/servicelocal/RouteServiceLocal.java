package netease.acdtest.servicelocal;



import netease.acdtest.param.ao.RouteAo;
import netease.acdtest.param.bo.AppRouteBo;
import netease.acdtest.param.bo.RouteBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由相关serviceLocal 接口
 *
 * @author mucongcong
 * @date 2022/06/02 09:42
 * @since
 **/
public interface RouteServiceLocal {

    /**
     * @Description 根据根节点的uuid查询该路由下所有的节点集合
     *
     * @Param [rootNodeId]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.RouteBo>
     **/
    List<RouteBo> getAllRouteNodeByRootNodeId(AppRouteBo appRootNode);

    /**
     * @Description 添加路由
     *
     * @Param [appId, role, routeIdentify路由标识, routeAo一整棵路由树]
     * @return java.lang.String
     **/
    String addRoutes(String appId, Integer role, String routeIdentify, RouteAo routeAo);

    /**
     * @Description 根据 坐席 属性map寻找队列，坐席可入多个队
     *
     * @Param [attributeMap, appId, routeIdentify]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.QueueBo>
     **/
    List<String> findSeatQueueByAttribute(Map<String, String> attributeMap, String appId, String routeIdentify);

    /**
     * @Description 根据 用户 属性map寻找队列，用户只入一个队
     *
     * @Param [attributeMap, appId, routeIdentify]
     * @return com.netease.we.voip.bs.acd.param.bo.QueueBo
     **/
    String findClientQueueByAttribute(Map<String, String> attributeMap, String appId, String routeIdentify);

    /**
     * @Description 根据appId和role查询所有路由树
     *
     * @Param [appId, role]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.RouteBo>
     **/
    Map<String,RouteBo> getRoute(String appId, Integer role, Integer limit, Integer offset);

    /**
     * @Description 编辑路由 返回该路由的标识
     *
     * @Param [appId, role, rootIdentify, routeAo]
     * @return java.lang.String
     **/
    String editRoute(String appId, Integer role, String rootIdentify,RouteAo routeAo);

    /**
     * @Description 删除节点树 返回删除的路由标识
     *
     * @Param [appId, role, rootIdentify]
     * @return java.lang.String
     **/
    String deleteRoute(String appId, Integer role,String rootIdentify);


    /**
     * @Description 根据appId查询路由信息
     *
     * @Param [appId]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.RouteBo>
     **/
    List<RouteBo> queryRouteByAppId(String appId);

    /**
     * @Description 根据appId和queueId查询路由信息
     *
     * @Param [appId, queueId]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.RouteBo>
     **/
    List<RouteBo> queryRouteByQueueId(String appId,String queueId);

    /**
     * @Description 根据appId与role返回正在使用的枚举值
     *
     * @Param [appId, role, propertyName]
     * @return java.util.List<java.lang.String>
     **/
    List<String> queryUsedPropertyValueByPropertyName(String appId, Integer role, String propertyName);

    /**
     * @Description 查询该appId与role下的所有用到的属性名称集合
     *
     * @Param [appId, role]
     * @return java.util.List<java.lang.String>
     **/
    Map<String, Set<String>> queryAllUsedNode (String appId, Integer role, Integer nodeType);

    /**
     * @Description 根据appId全部删除
     *
     * @Param [appId]
     * @return java.lang.Long
     **/
    void deleteAllByAppId(String appId);

    RouteBo getRouteByRootIdentify(String appId, Integer role, String rootIdentify);

}
