package netease.acdtest.servicelocal.impl;


import netease.acdtest.check.AppServiceLocalImplCheck;
import netease.acdtest.convert.AppConvert;
import netease.acdtest.dao.AppDao;
import netease.acdtest.entity.AppEntity;
import netease.acdtest.param.ao.AppAo;
import netease.acdtest.param.bo.AppBo;
import netease.acdtest.servicelocal.AppServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:55
 * @since
 */
@Service
public class AppServiceLocalImpl implements AppServiceLocal {

    @Autowired
    AppDao appDao;

//    @Autowired
//    RouteServiceLocal routeServiceLocal;
//
//    @Autowired
//    AppRouteServiceLocal appRouteServiceLocal;

//    @Autowired
//    QueueWeightLocalService queueWeightLocalService;
//
//    @Autowired
//    QueueRuleLocalService queueRuleLocalService;
//
//    @Autowired
//    QueueLocalService queueLocalService;
//
//    @Autowired
//    PropertyLocalService propertyLocalService;

    @Override
    public Long createApp(AppAo appAo) {
        AppServiceLocalImplCheck.createApp(appAo,appDao);
        return appDao.insert(AppConvert.convert(appAo));
    }

    @Override
    public Long editApp(AppAo appAo) {
        AppServiceLocalImplCheck.editApp(appAo,appDao);
        return appDao.update(AppConvert.convert(appAo));
    }

    @Override
    public List<AppBo> getAppListPage(Integer limit, Integer offset, String appId) {
        if (appId == null) {
            if (limit == null) {
                limit = 20;
            }
            if (offset == null) {
                offset = 0;
            }
            return AppConvert.convertToParams(appDao.queryList(limit, offset));
        } else {
            AppServiceLocalImplCheck.getAppListPage(appId,appDao);
            return Arrays.asList(getAppByAppId(appId));
        }
    }

    @Override
    public AppBo getAppByAppId(String appId) {
        AppServiceLocalImplCheck.getAppByAppId(appId);
        return AppConvert.convert(appDao.queryByAppId(appId));
    }

    @Override
    public Long deleteAppByAppName(String appName) {
        AppServiceLocalImplCheck.deleteAppByAppName(appName,appDao);
        AppEntity appEntity = appDao.queryByAppName(appName);
        return deleteAppByAppId(appEntity.getAppId());
    }

    @Override
    public AppBo getAppByAppName(String appName) {
        AppServiceLocalImplCheck.getAppByAppName(appName);
        return AppConvert.convert(appDao.queryByAppName(appName));
    }

    @Override
    public Long deleteAppByAppId(String appId) {
        AppServiceLocalImplCheck.deleteAppByAppId(appId,appDao);
//        routeServiceLocal.deleteAllByAppId(appId);
//        appRouteServiceLocal.deleteAllByAppId(appId);
//        queueWeightLocalService.deleteQueueWeightByAppId(appId);
//        queueRuleLocalService.deleteQueueRuleByAppId(appId);
//        queueLocalService.deleteQueueByAppId(appId);
//        propertyLocalService.deleteByAppId(appId);
        return appDao.deleteByAppId(appId);
    }


}