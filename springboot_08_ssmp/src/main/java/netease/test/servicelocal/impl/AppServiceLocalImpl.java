package netease.test.servicelocal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import netease.test.convert.AppConvert;
import netease.test.dao.AppDao;
import netease.test.entity.AppEntity;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;
import netease.test.servicelocal.AppServiceLocal;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:55
 * @since
 */
@Component
public class AppServiceLocalImpl implements AppServiceLocal {

    @Autowired
    AppDao appDao;

    /**
     * @Description 创建应用
     *
     * @Param [appAo]
     * @return void
     **/
    @Override
    public void createApp(AppAo appAo) {
        AppEntity appEntity = AppConvert.convert(appAo);
        appDao.insert(appEntity);
    }

    /**
     * @Description 编辑应用
     *
     * @Param [appAo]
     * @return void
     **/
    @Override
    public void editAppByAppId(AppAo appAo) {
        AppEntity appEntity = AppConvert.convert(appAo);
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(AppEntity::getAppId, appAo.getAppId());
        appDao.update(appEntity, lqw);
    }

    /**
     * @Description 删除应用
     *
     * @Param [appId]
     * @return void
     **/
    @Override
    public void deleteAppByAppId(String appId) {
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(AppEntity::getAppId, appId);
        appDao.delete(lqw);
    }

    /**
     * @Description 分页获取应用列表
     *
     * @Param [currentPage 当前页码, pageSize 每页的大小]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.netease.we.voip.bs.acd.param.bo.AppBo> mybatis-plus分页封装类型
     **/
    @Override
    public IPage<AppBo> getAppListPage(Integer currentPage, Integer pageSize, AppAo appAo) {
        IPage page = new Page(currentPage, pageSize);
        AppEntity appEntity = AppConvert.convert(appAo);
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper<AppEntity>();
        lqw.like(Strings.isNotEmpty(appEntity.getAppId()), AppEntity::getAppId,appEntity.getAppId())
                .like(Strings.isNotEmpty(appEntity.getAppName()), AppEntity::getAppName,appEntity.getAppName())
                .like(Strings.isNotEmpty(appEntity.getCallbackUrl()), AppEntity::getCallbackUrl,appEntity.getCallbackUrl());
        IPage iPage = appDao.selectPage(page, lqw);
        List<AppEntity> records = iPage.getRecords();
        List<AppBo> results = new ArrayList<>();
        for (AppEntity record : records) {
            AppBo convert = AppConvert.convert((record));
            results.add(convert);
        }
        iPage.setRecords(results);
        return iPage;
    }

    /**
     * @Description 根据appId查询应用详细数据
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.acd.param.bo.AppBo
     **/
    @Override
    public AppBo getAppByAppId(String appId) {
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(AppEntity::getAppId, appId);
        AppEntity appEntity = appDao.selectOne(lqw);
        AppBo appBo = AppConvert.convert(appEntity);
        return appBo;
    }

    @Override
    public void deleteAppByParam(AppAo appAo) {
        LambdaQueryWrapper<AppEntity> lqw = new LambdaQueryWrapper();
        AppEntity appEntity = AppConvert.convert(appAo);
        lqw.eq(AppEntity::getAppId, appEntity.getAppId())
                .eq(AppEntity::getAppName, appEntity.getAppName())
                .eq(AppEntity::getCallbackUrl, appEntity.getCallbackUrl())
                .eq(AppEntity::getStatus, appEntity.getStatus());
        appDao.delete(lqw);
    }


}