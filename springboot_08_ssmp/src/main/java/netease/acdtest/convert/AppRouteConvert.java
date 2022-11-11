package netease.acdtest.convert;


import netease.acdtest.entity.AppRouteEntity;
import netease.acdtest.param.ao.AppRouteAo;
import netease.acdtest.param.bo.AppRouteBo;
import netease.acdtest.utils.TimeUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * app-route转换
 *
 * @author mucongcong
 * @date 2022/06/06 10:48
 * @since
 **/
public class AppRouteConvert {

    public static AppRouteEntity convert(AppRouteAo param) {
        if (param == null) {
            return null;
        }
        AppRouteEntity result = new AppRouteEntity();
        BeanUtils.copyProperties(param, result);
        result.setDbCreateTime(TimeUtils.getCurrentTimestamp());
        result.setDbUpdateTime(TimeUtils.getCurrentTimestamp());
        return result;
    }

    public static List<AppRouteEntity> convertToEntities(List<AppRouteAo> params) {
        if (params == null) {
            return null;
        }
        if (params.size() == 0) {
            return new ArrayList<>();
        }
        return params.stream().map(AppRouteConvert::convert).collect(Collectors.toList());
    }

    public static List<AppRouteBo> convertToParams(List<AppRouteEntity> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.size() == 0) {
            return new ArrayList<>();
        }
        return entities.stream().map(AppRouteConvert::convert).collect(Collectors.toList());
    }

    public static AppRouteBo convert(AppRouteEntity entiry) {
        if (entiry == null) {
            return null;
        }
        AppRouteBo result = new AppRouteBo();
        BeanUtils.copyProperties(entiry, result);
        return result;
    }


}
