package netease.test.servicelocal.impl;


import netease.test.check.AppRouteServiceLocalImplCheck;
import netease.test.convert.AppRouteConvert;
import netease.test.dao.AppRouteDao;
import netease.test.dao.RouteDao;
import netease.test.entity.AppRouteEntity;
import netease.test.param.ao.AppRouteAo;
import netease.test.param.bo.AppRouteBo;
import netease.test.servicelocal.AppRouteServiceLocal;
import netease.test.servicelocal.AppServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用-路由service本地实现类
 *
 * @author mucongcong
 * @date 2022/06/02 09:48
 * @since
 **/
@Service
public class AppRouteServiceLocalImpl implements AppRouteServiceLocal {

    @Autowired
    AppRouteDao appRouteDao;

    @Autowired
    RouteDao routeDao;

    @Autowired
    AppServiceLocal appServiceLocal;

    @Override
    public List<AppRouteBo> getRootNodeByAppId(String appId, Integer role, Integer limit, Integer offset) {
        AppRouteServiceLocalImplCheck.getRootNodeByAppId(appId, role, appServiceLocal);
        if (limit == null) {
            limit = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        return AppRouteConvert.convertToParams(appRouteDao.queryByAppIdAndRole(appId, role,limit,offset));
    }

    @Override
    public List<AppRouteBo> getAllRootNodeByAppId(String appId, Integer role) {
        AppRouteServiceLocalImplCheck.getAllRootNodeByAppId(appId, role,appServiceLocal);
        return AppRouteConvert.convertToParams(appRouteDao.queryAllRouteByAppIdAndRole(appId, role));
    }

    @Override
    public Long deleteByAppIdAndRole(String appId, Integer role, String rootIdentify) {
        AppRouteServiceLocalImplCheck.deleteByAppIdAndRole(appId, role, rootIdentify,appServiceLocal);
        AppRouteEntity appRouteEntity = appRouteDao.queryByAppIdRoleAndRoot(appId, role, rootIdentify);
        appRouteEntity.setStatus(0);
        return appRouteDao.updateByAppIdAndRole(appRouteEntity);
    }

    @Override
    public String createNode(AppRouteAo rootNode) {
        //检查rootNode的getRootIdentify是否为空，为空生成
        AppRouteServiceLocalImplCheck.createNode(rootNode);
        appRouteDao.insert(AppRouteConvert.convert(rootNode));
        return rootNode.getRootIdentify();
    }

    @Override
    public String deleteByAppIdAndRoleTrue(String appId, Integer role,String rootIdentify) {
        AppRouteServiceLocalImplCheck.deleteByAppIdAndRoleTrue(appId, role, rootIdentify,appServiceLocal);
        AppRouteEntity appRouteEntity = appRouteDao.queryByAppIdRoleAndRoot(appId, role, rootIdentify);
        if (appRouteEntity != null) {
            routeDao.deleteByRootNodeIdTrue(appRouteEntity.getRootNodeId());
            appRouteDao.deleteByAppIdAndRoleTrue(appId, role, rootIdentify);
            return appRouteEntity.getRootIdentify();
        }
        return null;
    }

    @Override
    public AppRouteBo getSingleRootNode(String appId, Integer role, String rootIdentify) {
        AppRouteServiceLocalImplCheck.getSingleRootNode(appId, role, rootIdentify,appServiceLocal);
        return AppRouteConvert.convert(appRouteDao.queryByAppIdRoleAndRoot(appId, role, rootIdentify));
    }

    @Override
    public void deleteAllByAppId(String appId) {
        AppRouteServiceLocalImplCheck.deleteAllByAppId(appId,appServiceLocal);
        List<AppRouteEntity> appRouteEntities = appRouteDao.queryAllByAppId(appId);
        for (AppRouteEntity appRouteEntity : appRouteEntities) {
            deleteByAppIdAndRoleTrue(appRouteEntity.getAppId(), appRouteEntity.getRole(), appRouteEntity.getRootIdentify());
        }
    }



}
