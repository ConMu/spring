package netease.test.convert;


import javafx.util.Pair;
import netease.test.config.ACDConfig;
import netease.test.entity.RouteEntity;
import netease.test.enums.RouteNodeEnum;
import netease.test.param.ao.RouteAo;
import netease.test.param.bo.RouteBo;
import netease.test.utils.TimeUtils;
import netease.test.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * app-route转换
 *
 * @author mucongcong
 * @date 2022/06/06 10:48
 * @since
 **/
public class RouteConvert {

    public static RouteEntity convert(RouteAo param) {
        if (param == null) {
            return null;
        }
        RouteEntity result = new RouteEntity();
        BeanUtils.copyProperties(param, result);
        result.setDbCreateTime(TimeUtils.getCurrentTimestamp());
        result.setDbUpdateTime(TimeUtils.getCurrentTimestamp());
        return result;
    }

    public static List<RouteEntity> convertToEntities(List<RouteAo> params) {
        if (params == null) {
            return null;
        }
        if (params.size() == 0) {
            return new ArrayList<>();
        }
        return params.stream().map(RouteConvert::convert).collect(Collectors.toList());
    }

    public static List<RouteBo> convertToParams(List<RouteEntity> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.size() == 0) {
            return new ArrayList<>();
        }
        return entities.stream().map(RouteConvert::convert).collect(Collectors.toList());
    }

    public static RouteBo convert(RouteEntity entiry) {
        if (entiry == null) {
            return null;
        }
        RouteBo result = new RouteBo();
        BeanUtils.copyProperties(entiry, result);
        return result;
    }

    // k:route节点uuid v：route表的一行数据实体类
    public static HashMap<String, RouteBo> convertToMap (List<RouteBo> entiry) {
        if (entiry == null) {
            return null;
        }
        HashMap<String, RouteBo> result = new HashMap<>();
        for (RouteBo routeBo : entiry) {
            result.put(routeBo.getNodeId(), routeBo);
        }
        return result;
    }


    /**
     * @Description 传入一棵Ao树，初始化该树下所有的uuid返回<树根节点实体类,该树下所有实体类集合> isCreateNewTree创建新树时为true，更新树时为false
     *
     * @Param [routeAo]
     * @return javafx.util.Pair<com.netease.we.voip.bs.acd.entity.RouteEntity,java.util.List<com.netease.we.voip.bs.acd.entity.RouteEntity>>
     **/
    public static Pair<RouteEntity, List<RouteEntity>> convertToEntities(RouteAo routeAo,boolean isCreateNewTree) {
        List<RouteEntity> routeEntities = new ArrayList<>();
        //递归完成树到数据库对象的转化 1. 将子节点树转为entity的<String>childIds
        String rootNodeId = dfsCreateNode(routeAo, routeEntities);
        RouteEntity routeRootNode = null;
        if (isCreateNewTree) {
            //创建新树的情况
            //2. 将每个节点存储根节点的uuid 根节点也存储自己的uuid
            for (RouteEntity routeEntity : routeEntities) {
                routeEntity.setRootNodeId(rootNodeId);
                if (routeEntity.getNodeId().equals(rootNodeId)) {
                    routeRootNode = routeEntity;
                    routeEntity.setNodeType(RouteNodeEnum.ROOT_NODE.getCode());
                }
            }
        } else {
            //仅仅是更新树的情况，不需要更新每个节点的rootNodeId以及根节点标识
            for (RouteEntity routeEntity : routeEntities) {
                if (routeEntity.getNodeId().equals(rootNodeId)) {
                    routeRootNode = routeEntity;
                }
            }
        }
        Pair<RouteEntity, List<RouteEntity>> resultMap = new Pair<>(routeRootNode, routeEntities);
        return resultMap;
    }

    /**
     * @Description 递归，传入一棵树，传出该节点在数据库的uuid，用于存于父节点childIds中
     *
     * @Param [routeAoList]
     * @return java.lang.Long
     **/
    private static String dfsCreateNode(RouteAo node, List<RouteEntity> routeEntities) {
        if (node.getChildNodes().isEmpty()) {
            //没有子节点的节点为队列节点
            RouteEntity route = convert(node);
            String uuid = UUIDUtils.genUUID();
            route.setNodeId(uuid);
            route.setNodeType(RouteNodeEnum.QUEUE_NODE.getCode());
            routeEntities.add(route);
            return uuid;
        }
        StringBuffer childIdsStr = new StringBuffer();
        // 获取子节点Ao并遍历
        List<RouteAo> childNodes = node.getChildNodes();
        for (RouteAo childNode : childNodes) {
            String childId = dfsCreateNode(childNode, routeEntities);
            childIdsStr.append(childId).append(ACDConfig.SPLIT_REGEX);
        }
        node.setChildIds(Objects.requireNonNull(childIdsStr).toString());
        RouteEntity route = convert(node);
        String uuid = UUIDUtils.genUUID();
        route.setNodeId(uuid);
        route.setNodeType(RouteNodeEnum.ENUM_VALUE_NODE.getCode());
        routeEntities.add(route);
        return uuid;
    }

    public static RouteBo convertAoToBo(RouteAo routeAo) {
        RouteBo result = dfsConvertAoToBo(routeAo);
        return result;
    }

    private static RouteBo dfsConvertAoToBo(RouteAo routeAo) {
        RouteBo routeBo = convertPropertiesAoToBo(routeAo);
        if (routeAo != null) {
            List<RouteBo> childBos = new ArrayList<>();
            if (routeAo.getChildNodes() != null) {
                for (RouteAo childNode : routeAo.getChildNodes()) {
                    childBos.add(dfsConvertAoToBo(childNode));
                }
            }
            routeBo.setChildNodes(childBos);
        }
        return routeBo;
    }

    public static RouteBo convertPropertiesAoToBo(RouteAo param) {
        if (param == null) {
            return null;
        }
        RouteBo result = new RouteBo();
        BeanUtils.copyProperties(param, result);
        result.setDbCreateTime(TimeUtils.getCurrentTimestamp());
        result.setDbUpdateTime(TimeUtils.getCurrentTimestamp());
        return result;
    }

    public static RouteAo convertBoToAo(RouteBo routeBo) {
        RouteAo result = dfsConvertBoToAo(routeBo);
        return result;
    }

    private static RouteAo dfsConvertBoToAo(RouteBo routeBo) {
        RouteAo routeAo = convertPropertiesBoToAo(routeBo);
        if (routeBo != null) {
            List<RouteAo> childAos = new ArrayList<>();
            if (routeBo.getChildNodes() != null) {
                for (RouteBo childNode : routeBo.getChildNodes()) {
                    childAos.add(dfsConvertBoToAo(childNode));
                }
            }
            routeAo.setChildNodes(childAos);
        }
        return routeAo;
    }

    private static RouteAo convertPropertiesBoToAo(RouteBo param) {
        if (param == null) {
            return null;
        }
        RouteAo result = new RouteAo();
        BeanUtils.copyProperties(param, result);
        result.setDbCreateTime(TimeUtils.getCurrentTimestamp());
        result.setDbUpdateTime(TimeUtils.getCurrentTimestamp());
        return result;
    }
}
