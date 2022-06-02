package netease.test.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;

/**
 * 应用service
 */
public interface AppService {

    /**
     * @Description 创建应用
     *
     * @Param [appAo]
     * @return void
     **/
    void createApp(AppAo appAo);

    /**
     * @Description 编辑应用
     *
     * @Param [appAo]
     * @return void
     **/
    void editAppByAppId(AppAo appAo);

    /**
     * @Description 删除应用
     *
     * @Param [appId]
     * @return void
     **/
    void deleteAppByAppId(String appId);

    /**
     * @Description 分页获取应用列表
     *
     * @Param [currentPage 当前页码, pageSize 每页的大小]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.netease.we.voip.bs.acd.param.bo.AppBo> mybatis-plus分页封装类型
     **/
    IPage<AppBo> getAppListPage(Integer currentPage, Integer pageSize, AppAo appAo);

    /**
     * @Description 根据appId查询应用详细数据
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.acd.param.bo.AppBo
     **/
    AppBo getAppByAppId(String appId);

    void deleteAppByParam(AppAo appAo);
}
