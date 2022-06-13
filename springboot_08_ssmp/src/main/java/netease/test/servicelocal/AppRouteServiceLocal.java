package netease.test.servicelocal;




import netease.test.param.ao.AppRouteAo;
import netease.test.param.bo.AppRouteBo;

import java.util.List;

/**
 * 应用-路由相关serviceLocal 接口
 *
 * @author mucongcong
 * @date 2022/5/31  21:54
 * @since
 */
public interface AppRouteServiceLocal {

    /**
     * @Description 根据appId与role查询根节点,支持分页功能
     *
     * @Param [appId, role]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.AppRouteBo>
     **/
    List<AppRouteBo> getRootNodeByAppId(String appId, Integer role, Integer limit, Integer offset);

    /**
     * @Description 根据appId与role查询所有根节点
     *
     * @Param [appId, role, limit, offset]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.AppRouteBo>
     **/
    List<AppRouteBo> getAllRootNodeByAppId(String appId, Integer role);

    /**
     * @Description 根据appId，role，标识假删除路由
     *
     * @Param [appId, role, rootIdentify]
     * @return java.lang.Long
     **/
    Long deleteByAppIdAndRole(String appId, Integer role, String rootIdentify);

    /**
     * @Description 插入根节点到app_route表中
     *
     * @Param [rootNode]
     * @return java.lang.Long
     **/
    String createNode(AppRouteAo rootNode);

    /**
     * @Description 真删除
     *
     * @Param [appId, role, rootIdentify]
     * @return java.lang.String
     **/
    String deleteByAppIdAndRoleTrue(String appId, Integer role, String rootIdentify);

    /**
     * @Description 获取单个路由根节点信息
     *
     * @Param [appId, role, rootIdentify]
     * @return com.netease.we.voip.bs.acd.param.bo.AppRouteBo
     **/
    AppRouteBo getSingleRootNode(String appId, Integer role, String rootIdentify);

    /**
     * @Description 根据appId全部删除
     *
     * @Param [appId]
     * @return java.lang.Long
     **/
    void deleteAllByAppId(String appId);

}
