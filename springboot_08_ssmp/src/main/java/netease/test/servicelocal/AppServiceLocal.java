package netease.test.servicelocal;


import com.baomidou.mybatisplus.core.metadata.IPage;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;

/**
 * 应用相关serviceLocal 接口
 *
 * @author dujun01
 * @date 2022/5/31  21:54
 * @since
 */
public interface AppServiceLocal {


    void createApp(AppAo appAo);

    void editAppByAppId(AppAo appAo);

    void deleteAppByAppId(String appId);

    IPage<AppBo> getAppListPage(Integer currentPage, Integer pageSize, AppAo appAo);

    AppBo getAppByAppId(String appId);

    void deleteAppByParam(AppAo appAo);
}