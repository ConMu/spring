package netease.acdtest.dao;


import netease.acdtest.entity.AppRouteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AppRouteDAO继承基类
 */
@Mapper
public interface AppRouteDao {

    String cachePre = "'voip_acd_app_route'";

    Long updateByAppIdAndRole(AppRouteEntity convert);
    Long insert(AppRouteEntity convert);

    Long deleteByAppIdAndRoleTrue(@Param("appId") String appId, @Param("role")  Integer role, @Param("rootIdentify")String rootIdentify);

    Long deleteAllByAppId(String appId);

    List<AppRouteEntity> queryByAppIdAndRole(@Param("appId") String appId, @Param("role") Integer role, @Param("limit") Integer limit, @Param("offset") Integer offset);

    AppRouteEntity queryByAppIdRoleAndRoot(@Param("appId") String appId, @Param("role") Integer role, @Param("rootIdentify")String rootIdentify);

    List<AppRouteEntity> queryAllRouteByAppIdAndRole(@Param("appId") String appId,@Param("role")  Integer role);

    List<AppRouteEntity> queryAllByAppId(String appId);
}