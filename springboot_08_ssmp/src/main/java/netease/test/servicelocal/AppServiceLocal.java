package netease.test.servicelocal;



import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;

import java.util.List;

/**
 * 应用相关serviceLocal 接口
 *
 * @author dujun01
 * @date 2022/5/31  21:54
 * @since
 */
public interface AppServiceLocal {

    /**
     * @Description 创建应用
     *
     * @Param [appAo]
     * @return void
     **/
    Long createApp(AppAo appAo);

    /**
     * @Description 编辑应用
     *
     * @Param [appAo]
     * @return void
     **/
    Long editApp(AppAo appAo);

    /**
     * @Description 根据appId删除应用
     *
     * @Param [appId]
     * @return void
     **/
    Long deleteAppByAppId(String appId);

    /**
     * @Description 分页查询
     *
     * @Param [limit, offset, appId]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.AppBo>
     **/
    List<AppBo> getAppListPage(Integer limit, Integer offset, String appId);

    /**
     * @Description 根据appId查询应用
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.acd.param.bo.AppBo
     **/
    AppBo getAppByAppId(String appId);

    /**
     * @Description 根据appName删除
     *
     * @Param [appName]
     * @return java.lang.Long
     **/
    Long deleteAppByAppName(String appName);

    /**
     * @Description 根据appName查询
     *
     * @Param [limit, offset, appName]
     * @return java.util.List<com.netease.we.voip.bs.acd.param.bo.AppBo>
     **/
    AppBo getAppByAppName(String appName);
}