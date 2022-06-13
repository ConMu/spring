package netease.test.check;


import com.alibaba.druid.util.StringUtils;
import netease.test.dao.AppDao;
import netease.test.entity.AppEntity;
import netease.test.exception.AppRuntimeException;
import netease.test.exception.code.AppErrorCodeEnums;
import netease.test.param.ao.AppAo;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:56
 * @since
 */
public class AppServiceLocalImplCheck {
    public static void check(AppAo appAo) {
    }

    public static void getAppListPage(String appId, AppDao appDao) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appDao.queryByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
    }

    public static void getAppByAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        }
    }

    public static void deleteAppByAppId(String appId, AppDao appDao) {
        if (StringUtils.isEmpty(appId)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appDao.queryByAppId(appId) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
    }

    public static void deleteAppByAppName(String appName, AppDao appDao) {
        if (StringUtils.isEmpty(appName)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不可为空");
        } else {
            if (appDao.queryByAppName(appName) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不存在");
            }
        }
    }

    public static void getAppByAppName(String appName) {
        if (StringUtils.isEmpty(appName)) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不可为空");
        }
    }

    public static void createApp(AppAo appAo, AppDao appDao) {
        if (appAo == null) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "请求参数不可为空");
        }
        if (StringUtils.isEmpty(appAo.getAppId())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appDao.queryByAppId(appAo.getAppId()) != null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId已存在");
            }
            if (!AppIdCheck.isLetterDigit(appAo.getAppId())) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId仅能由字母与数字构成");
            }
        }
        if (StringUtils.isEmpty(appAo.getAppName())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不可为空");
        } else {
            if (appDao.queryByAppName(appAo.getAppName()) != null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName已存在");
            }
        }
        if (StringUtils.isEmpty(appAo.getCallbackUrl())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "回调地址不可为空");
        } else {
            if (!UrlCheck.checkUrl(appAo.getCallbackUrl())) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "回调地址无法连接");
            }
        }
        if (appAo.getStatus() == null) {
            appAo.setStatus(1);
        }

    }

    public static void editApp(AppAo appAo, AppDao appDao) {
        if (appAo == null) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "请求参数不可为空");
        }
        if (StringUtils.isEmpty(appAo.getAppId())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不可为空");
        } else {
            if (appDao.queryByAppId(appAo.getAppId()) == null) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appId不存在");
            }
        }
        if (StringUtils.isEmpty(appAo.getAppName())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不可为空");
        } else {
            AppEntity appEntity = appDao.queryByAppName(appAo.getAppName());
            if (appEntity != null && !appEntity.getAppId().equals(appAo.getAppId())) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "appName不能重复");
            }
        }
        if (StringUtils.isEmpty(appAo.getCallbackUrl())) {
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "回调地址不可为空");
        } else {
            if (!UrlCheck.checkUrl(appAo.getCallbackUrl())) {
                throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "回调地址无法连接");
            }
        }
        if (appAo.getStatus() == null) {
            appAo.setStatus(1);
        }
    }
}