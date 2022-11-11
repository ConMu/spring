package netease.acdtest.check;



import com.alibaba.druid.util.StringUtils;
import netease.acdtest.enums.RoleEnum;
import netease.acdtest.enums.RouteNodeEnum;
import netease.acdtest.exception.AppRuntimeException;
import netease.acdtest.exception.code.AppErrorCodeEnums;
import netease.acdtest.param.bo.AppRouteBo;
import netease.acdtest.servicelocal.AppRouteServiceLocal;
import netease.acdtest.servicelocal.AppServiceLocal;

/**
 * 路由树check
 *
 * @author mucongcong
 * @date 2022/06/07 11:41
 * @since
 **/
public class RouteServiceLocalImplCheck {

    public static void queryRouteByAppId(String appId, AppServiceLocal appServiceLocal){
        if (StringUtils.isEmpty(appId)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
        } else if (appServiceLocal.getAppByAppId(appId) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
        }
    }

//    public static void queryRouteByQueueId(String appId, String queueId, AppServiceLocal appServiceLocal, QueueBaseLocalService queueBaseLocalService){
//        if (StringUtils.isEmpty(appId)){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
//        } else if (appServiceLocal.getAppByAppId(appId) == null){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
//        }
//
//        if (StringUtils.isEmpty(queueId)){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"queueId不能为空");
//        } else if (queueBaseLocalService.queryQueueByQueueId(appId,queueId) == null){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"queueId非法");
//        }
//    }

    public static void queryUsedPropertyValueByPropertyName(String appId, Integer role, String propertyName, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
        } else if (appServiceLocal.getAppByAppId(appId) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
        if (StringUtils.isEmpty(propertyName)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "节点属性名称不能为空");
        }

    }

    public static void queryAllUsedNode(String appId, Integer role, Integer nodeType, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
        } else if (appServiceLocal.getAppByAppId(appId) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
        if (nodeType == null) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "需要指定节点类型");
        } else if (!nodeType.equals(RouteNodeEnum.ROOT_NODE.getCode())
                && !nodeType.equals(RouteNodeEnum.ENUM_VALUE_NODE.getCode())
                && !nodeType.equals(RouteNodeEnum.QUEUE_NODE.getCode())){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "节点类型非法");
        }
    }

    public static void deleteAllByAppId(String appId, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
        } else if (appServiceLocal.getAppByAppId(appId) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
        }
    }

    public static void getAllRouteNodeByRootNodeId(AppRouteBo appRootNode, AppRouteServiceLocal appRouteServiceLocal) {
        if (appRootNode == null
                || appRouteServiceLocal.getSingleRootNode(appRootNode.getAppId(), appRootNode.getRole(), appRootNode.getRootIdentify()) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"根节点不存在");
        }
    }

    public static void deleteRoute(String appId, Integer role, String rootIdentify, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
        } else if (appServiceLocal.getAppByAppId(appId) == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定删除的角色");
        }
        if (StringUtils.isEmpty(rootIdentify)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由标识不能为空");
        }
    }

//    public static void checkRoute(String appId, Integer role, String routeIdentify, RouteAo routeAo, AppServiceLocal appServiceLocal, QueueBaseLocalService queueBaseLocalService, PropertyLocalServiceImpl propertyLocalService, AppRouteServiceLocal appRouteServiceLocal) {
//        if (StringUtils.isEmpty(appId)){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId不能为空");
//        } else if (appServiceLocal.getAppByAppId(appId) == null){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"appId非法");
//        }
//        if (role == null){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
//        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
//        }
//        if (StringUtils.isEmpty(routeIdentify)){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由标识不能为空");
//        } else if (appRouteServiceLocal.getSingleRootNode(appId, role, routeIdentify) != null) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"已存在相同的路由标识");
//        }
//        if (appRouteServiceLocal.getAllRootNodeByAppId(appId, role).size() == ACDConfig.ROUTE_MAX_SIZE) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由树数量达到最大值：" + ACDConfig.ROUTE_MAX_SIZE);
//        }
//        //根节点校验
//        if (routeAo == null) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由树不能为空");
//        } else if (routeAo.getEnumValue() != null
//                || routeAo.getPropertyIdentify() != null || routeAo.getPropertyName() != null
//                || routeAo.getQueueId() != null || routeAo.getQueueName() != null
//                || !routeAo.getNodeType().equals(RouteNodeEnum.ROOT_NODE.getCode())) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由树根节点非法");
//        }
//        //节点校验
//        Map<String, PropertyBo> propertyMaps = propertyLocalService.getAllByAppIdAndRole(appId, role);
//        Map<String, QueueBo> queueBos = queueBaseLocalService.queryAllQueueByAppId(appId);
//        List<RouteAo> childNodes = routeAo.getChildNodes();
//        if (childNodes == null) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由树未配置完全");
//        }
//        for (RouteAo childNode : childNodes) {
//            dfsCheckTree(childNode, propertyMaps, queueBos);
//        }
//
//    }
//
//    private static void dfsCheckTree(RouteAo routeAo, Map<String, PropertyBo> propertyMaps, Map<String, QueueBo> queueBos) {
//        List<RouteAo> childNodes = routeAo.getChildNodes();
//        if (childNodes != null) {
//            //此时是属性节点
//            if (StringUtils.isEmpty(routeAo.getPropertyName())
//                    || StringUtils.isEmpty(routeAo.getPropertyIdentify())
//                    || StringUtils.isEmpty(routeAo.getEnumValue())) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "路由树未配置完全");
//            } else if (!propertyMaps.containsKey(routeAo.getPropertyName())) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "属性节点非法");
//            } else if (!propertyMaps.get(routeAo.getPropertyName()).getEnumValue().contains(routeAo.getPropertyName())){
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "属性枚举值非法");
//            }
//            for (RouteAo childNode : childNodes) {
//                dfsCheckTree(childNode, propertyMaps, queueBos);
//            }
//        } else {
//            //此时是队列节点
//            if (StringUtils.isEmpty(routeAo.getQueueId())
//                    || StringUtils.isEmpty(routeAo.getQueueName())) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由树未配置完全");
//            } else if (!queueBos.containsKey(routeAo.getQueueId())) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"队列节点非法");
//            } else if (!queueBos.get(routeAo.getQueueId()).getQueueName().equals(routeAo.getQueueName())) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"队列名称非法");
//            }
//        }
//    }
}
