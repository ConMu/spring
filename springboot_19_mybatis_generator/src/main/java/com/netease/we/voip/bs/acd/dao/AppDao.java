package com.netease.we.voip.bs.acd.dao;

import com.netease.we.voip.bs.acd.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:57
 * @since
 */
@Mapper
@Repository
public interface AppDao {

    Long createApp(AppEntity appEntity);

    Long deleteAppByAppId(@Param("appId") String appId);

    Long updateApp(AppEntity appEntity);

    List<AppEntity> queryApp(@Param("limit") Integer limit, @Param("offset") Integer offset);

}
