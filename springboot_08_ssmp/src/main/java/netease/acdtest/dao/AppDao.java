package netease.acdtest.dao;


import netease.acdtest.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:57
 * @since
 */
@Mapper
public interface AppDao {

    String cachePre = "'voip_acd_app'";

    Long insert(AppEntity entity);

    Long update(AppEntity entity);

    Long deleteByAppId(@Param("appId") String appId);

    List<AppEntity> queryList(@Param("limit") Integer limit, @Param("offset") Integer offset);

    AppEntity queryByAppId (@Param("appId") String appId);

    Long deleteByAppName(@Param("appName") String appName);

    AppEntity queryByAppName(@Param("appName") String appName);
}
