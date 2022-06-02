package netease.test.convert;



import netease.test.entity.AppEntity;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppConvert {
    public static AppEntity convert(AppAo param) {
        if (param == null) {
            return null;
        }
        AppEntity result = new AppEntity();
        BeanUtils.copyProperties(param, result);
        result.setDbCreateTime(null);
        result.setDbUpdateTime(null);
        return result;
    }

    public static List<AppEntity> convertToEntities(List<AppAo> params) {
        if (params == null) {
            return null;
        }
        if (params.size() == 0) {
            return new ArrayList<>();
        }
        return params.stream().map(AppConvert::convert).collect(Collectors.toList());
    }

    public static List<AppBo> convertToParams(List<AppEntity> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.size() == 0) {
            return new ArrayList<>();
        }
        return entities.stream().map(AppConvert::convert).collect(Collectors.toList());
    }

    public static AppBo convert(AppEntity entiry) {
        if (entiry == null) {
            return null;
        }
        AppBo result = new AppBo();
        BeanUtils.copyProperties(entiry, result);
        return result;
    }
}
