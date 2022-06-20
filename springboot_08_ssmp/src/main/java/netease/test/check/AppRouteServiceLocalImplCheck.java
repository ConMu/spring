package netease.test.check;


import com.alibaba.druid.util.StringUtils;
import netease.test.enums.RoleEnum;
import netease.test.exception.AppRuntimeException;
import netease.test.exception.code.AppErrorCodeEnums;
import netease.test.param.ao.AppRouteAo;
import netease.test.servicelocal.AppServiceLocal;
import netease.test.utils.UUIDUtils;

import java.util.Map;

/**
 * 应用-根节点check
 *
 * @author mucongcong
 * @date 2022/06/07 11:46
 * @since
 **/
public class AppRouteServiceLocalImplCheck {
    public static void check(String appId, Integer role) {

    }

    public static void checkdeleteSingleRoute(String appId, Integer role, String rootIdentify) {

    }

    public static void checkNode(AppRouteAo rootNode) {
        if (rootNode.getRootIdentify() == null) {
            rootNode.setRootNodeId(UUIDUtils.genOriUUID());
        }
    }

    public static void getSingleRootNode(String appId, Integer role, String rootIdentify, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
        if (StringUtils.isEmpty(rootIdentify)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "路由标识不能为空");
        }

    }

//    public static void checkAttribute(Map<String, String> attributeMap, String appId, String routeIdentify, Integer role, PropertyLocalServiceImpl propertyLocalService, AppServiceLocal appServiceLocal) {
//        if (StringUtils.isEmpty(appId)) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
//        } else {
//            if (appServiceLocal.getAppByAppId(appId) == null) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
//            }
//        }
//        if (role == null){
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
//        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
//        }
//        if (StringUtils.isEmpty(routeIdentify)) {
//            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "请指定一个路由树");
//        }
//        Map<String, PropertyBo> attrMap = propertyLocalService.getAllByAppIdAndRole(appId, role);
//        for (String attr : attributeMap.keySet()) {
//            if (!attrMap.containsKey(attr)) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "属性名称配置不正确");
//            }
//        }
//    }

    public static void checkAttribute(Map<String, String> attributeMap, String appId, String routeIdentify, Integer role, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
        if (StringUtils.isEmpty(routeIdentify)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "请指定一个路由树");
        }
//        Map<String, PropertyBo> attrMap = propertyLocalService.getAllByAppIdAndRole(appId, role);
//        for (String attr : attributeMap.keySet()) {
//            if (!attrMap.containsKey(attr)) {
//                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "属性名称配置不正确");
//            }
//        }
    }

    public static void deleteAllByAppId(String appId, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
    }

    public static void getRoute(String appId, Integer role, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
    }

    public static void getRootNodeByAppId(String appId, Integer role, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role != null && !role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
    }

    public static void getSingleRootByRootId(String rootId) {
        if (StringUtils.isEmpty(rootId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "路由id不可为空");
        }
    }

    public static void deleteByAppIdAndRoleTrue(String appId, Integer role, String rootIdentify, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定删除的角色");
        }
        if (StringUtils.isEmpty(rootIdentify)){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"路由标识不能为空");
        }
    }

    public static void getAllRootNodeByAppId(String appId, Integer role, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
    }

    public static void deleteByAppIdAndRole(String appId, Integer role, String rootIdentify, AppServiceLocal appServiceLocal) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appServiceLocal.getAppByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (role == null){
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(),"需要指定角色");
        }else if (!role.equals(RoleEnum.Role_Seat.getKey()) && !role.equals(RoleEnum.Role_Client.getKey())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "角色属性值非法");
        }
    }

    public static void createNode(AppRouteAo rootNode) {
        if (rootNode == null) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "根节点非法");
        }
    }
}
