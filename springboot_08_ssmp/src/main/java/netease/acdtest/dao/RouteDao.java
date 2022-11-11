package netease.acdtest.dao;


import netease.acdtest.entity.RouteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * RouteDao继承基类
 */
@Mapper
public interface RouteDao{

    String cachePre = "'voip_acd_route'";

    Long insert(RouteEntity entity);

    Long deleteByRootNodeIdTrue(@Param("rootNodeId") String rootNodeId);

    Long deleteByNodeId(String nodeId);

    Long deleteInNodeIds(List<String> needDeleteNodeIds, String rootNodeId);

    List<RouteEntity> queryUsedNodeByPropertyName(@Param("appId") String appId, List<String> rootNodeIds, @Param("propertyName") String propertyName);

    List<RouteEntity> queryAllUsedNode(@Param("appId") String appId, List<String> rootNodeIds, @Param("nodeType") Integer nodeType);

    List<RouteEntity> queryByRootNodeId(@Param("rootNodeId")String rootNodeId);

    List<RouteEntity> queryByAppId(@Param("appId") String appId);

    List<RouteEntity> queryByQueueId(String queueId);

    Long deleteAllByAppId(@Param("appId") String appId);

}