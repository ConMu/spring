package netease.test.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;
import netease.test.service.AppService;
import netease.test.servicelocal.AppServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:53
 * @since
 */
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    AppServiceLocal appServiceLocal;


    @Override
    public void createApp(AppAo appAo) {
        appServiceLocal.createApp(appAo);
    }

    @Override
    public void editAppByAppId(AppAo appAo) {
        appServiceLocal.editAppByAppId(appAo);
    }

    @Override
    public void deleteAppByAppId(String appId) {
        appServiceLocal.deleteAppByAppId(appId);
    }

    @Override
    public IPage<AppBo> getAppListPage(Integer currentPage, Integer pageSize, AppAo appAo) {
        IPage<AppBo> appBos = appServiceLocal.getAppListPage(currentPage,pageSize,appAo);
        return appBos;
    }

    @Override
    public AppBo getAppByAppId(String appId) {
        AppBo appBo = appServiceLocal.getAppByAppId(appId);
        return appBo;
    }

    @Override
    public void deleteAppByParam(AppAo appAo) {
        appServiceLocal.deleteAppByParam(appAo);
    }


}