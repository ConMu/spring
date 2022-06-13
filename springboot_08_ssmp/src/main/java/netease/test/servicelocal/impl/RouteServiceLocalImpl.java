package netease.test.servicelocal.impl;


import javafx.util.Pair;
import netease.test.check.AppRouteServiceLocalImplCheck;
import netease.test.check.RouteServiceLocalImplCheck;
import netease.test.config.ACDConfig;
import netease.test.convert.RouteConvert;
import netease.test.dao.RouteDao;
import netease.test.entity.RouteEntity;
import netease.test.enums.RoleEnum;
import netease.test.enums.RouteNodeEnum;
import netease.test.exception.AppRuntimeException;
import netease.test.exception.code.AppErrorCodeEnums;
import netease.test.param.ao.AppRouteAo;
import netease.test.param.ao.RouteAo;
import netease.test.param.bo.AppRouteBo;
import netease.test.param.bo.RouteBo;
import netease.test.servicelocal.AppRouteServiceLocal;
import netease.test.servicelocal.AppServiceLocal;
import netease.test.servicelocal.RouteServiceLocal;
import netease.test.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 路由service本地实现类
 *
 * @author mucongcong
 * @date 2022/06/02 09:48
 * @since
 **/
@Service
public class RouteServiceLocalImpl implements RouteServiceLocal {

    @Autowired
    RouteDao routeDao;

    @Autowired
    AppRouteServiceLocal appRouteServiceLocal;

    @Autowired
    AppServiceLocal appServiceLocal;

//    @Autowired
//    QueueBaseLocalService queueBaseLocalService;
//
//    @Autowired
//    PropertyLocalServiceImpl propertyLocalService;

    @Override
    public List<RouteBo> getAllRouteNodeByRootNodeId(AppRouteBo appRootNode) {
        RouteServiceLocalImplCheck.getAllRouteNodeByRootNodeId(appRootNode, appRouteServiceLocal);
        return RouteConvert.convertToParams(routeDao.queryByRootNodeId(appRootNode.getRootNodeId()));
    }

    @Override
    public String addRoutes(String appId, Integer role, String routeIdentify, RouteAo routeAo) {
//        RouteServiceLocalImplCheck.checkRoute(appId,role,routeIdentify,routeAo, appServiceLocal,queueBaseLocalService,propertyLocalService,appRouteServiceLocal);
        // k:根节点实体类 v：该根节点下所有的节点实体类
        Pair<RouteEntity, List<RouteEntity>> pair = RouteConvert.convertToEntities(routeAo,true);
        // 根节点插入到app_route表中
        String nodeIdentify = appRouteServiceLocal.createNode(routeToAppRoute(pair.getKey(), routeIdentify,appId,role));
        for (RouteEntity routeEntity : pair.getValue()) {
            //子节点插入到route表中
            routeDao.insert(routeEntity);
        }
        return nodeIdentify;
    }

    /**
     * @Description 将route根节点转变为app_root数据
     *
     * @Param [rootNode]
     * @return com.netease.we.voip.bs.acd.param.ao.AppRouteAo
     **/
    private AppRouteAo routeToAppRoute(RouteEntity rootNode, String routeIdentify, String appId, Integer role) {
        AppRouteAo appRouteAo = new AppRouteAo();
        appRouteAo.setAppId(appId);
        appRouteAo.setRootNodeId(rootNode.getNodeId());
        if (routeIdentify == null) {
            routeIdentify = UUIDUtils.genOriUUID();
        }
        appRouteAo.setRole(role);
        appRouteAo.setRootIdentify(routeIdentify);
        return appRouteAo;
    }

    @Override
    public List<String> findSeatQueueByAttribute(Map<String, String> attributeMap, String appId, String routeIdentify) {
        //如果routeIdentify为空，赋值一个默认路由
//        AppRouteServiceLocalImplCheck.checkAttribute(attributeMap,appId, routeIdentify, RoleEnum.Role_Seat.getKey(),propertyLocalService,appServiceLocal);
        AppRouteServiceLocalImplCheck.checkAttribute(attributeMap,appId, routeIdentify, RoleEnum.Role_Seat.getKey(),appServiceLocal);
        return getQueueIdsList(attributeMap, appId, routeIdentify, RoleEnum.Role_Seat.getKey());
    }

    @Override
    public String findClientQueueByAttribute(Map<String, String> attributeMap, String appId, String routeIdentify) {
        //如果routeIdentify为空，赋值一个默认路由
//        AppRouteServiceLocalImplCheck.checkAttribute(attributeMap,appId, routeIdentify,RoleEnum.Role_Client.getKey(),propertyLocalService,appServiceLocal);
        AppRouteServiceLocalImplCheck.checkAttribute(attributeMap,appId, routeIdentify,RoleEnum.Role_Client.getKey(),appServiceLocal);
        return getQueueIdsList(attributeMap, appId, routeIdentify, RoleEnum.Role_Client.getKey()).get(0);
    }

    /**
     * @Description 返回查找到的队列uuid集合
     *
     * @Param [attributeMap, appId, routeIdentify, role]
     * @return java.util.List<java.lang.String>
     **/
    public List<String> getQueueIdsList(Map<String, String> attributeMap, String appId, String routeIdentify, Integer role) {
        List<String> resultQueueIds = new ArrayList<>();
        AppRouteBo appRootNode = appRouteServiceLocal.getSingleRootNode(appId, role, routeIdentify);
        RouteBo rootNode = createTreeByRootNodeId(appRootNode);
        matchAttributeAndGetQueue(attributeMap,rootNode,resultQueueIds);
        if (resultQueueIds.isEmpty()) {
            //Todo 返回默认队列，暂时先抛异常
            throw new AppRuntimeException(AppErrorCodeEnums.BAD_REQUEST.getCode(), "属性匹配不到路由树");
        }
        return resultQueueIds;
    }

    /**
     * @Description 根据根节点uuid创建该路由树
     *
     * @Param [rootNodeId]
     * @return com.netease.we.voip.bs.acd.param.bo.RouteBo
     **/
    public RouteBo createTreeByRootNodeId(AppRouteBo appRootNode) {
        List<RouteBo> routeBos = getAllRouteNodeByRootNodeId(appRootNode);
        //k:所有节点的uuid v：route表的一行数据实体类
        HashMap<String, RouteBo> routeBoHashMap = RouteConvert.convertToMap(routeBos);
        //取得根节点
        RouteBo rootNode = routeBoHashMap.get(appRootNode.getRootNodeId());
        // 1.递归完成树构建； 2.封装树的根节点
        dfsCreateTree(rootNode,routeBoHashMap);
        return rootNode;
    }

    /**
     * @Description 递归寻找路由
     *
     * @Param [attributeMap, node, resultQueueIds, attrEnum 只在枚举节点的时候才有值：当前map对应的枚举值]
     * @return void
     **/
    private void matchAttributeAndGetQueue(Map<String, String> attributeMap, RouteBo node, List<String> resultQueueIds) {
        if (node.getNodeType().equals(RouteNodeEnum.ENUM_VALUE_NODE.getCode())) {
            //属性：枚举值 节点的情况
            if (attributeMap.containsKey(node.getPropertyName())) {
                String attrValue = attributeMap.get(node.getPropertyName());
                //该节点下所有的枚举值
                List<String> enumValues = Arrays.asList(node.getEnumValue().split(ACDConfig.SPLIT_REGEX));
                if (attrValue != null && enumValues.contains(attrValue)) {
                    List<RouteBo> childNodes = node.getChildNodes();
                    for (RouteBo childNode : childNodes) {
                        matchAttributeAndGetQueue(attributeMap,childNode,resultQueueIds);
                    }
                }
            }
        } else if (node.getNodeType().equals(RouteNodeEnum.ROOT_NODE.getCode())) {
            //根节点的情况
            List<RouteBo> childNodes = node.getChildNodes();
            for (RouteBo childNode : childNodes) {
                matchAttributeAndGetQueue(attributeMap, childNode, resultQueueIds);
            }
        } else {
            //队列的情况
            resultQueueIds.add(node.getQueueId());
        }
    }

    @Override
    public Map<String,RouteBo> getRoute(String appId, Integer role, Integer limit, Integer offset) {
        AppRouteServiceLocalImplCheck.getRoute(appId, role, appServiceLocal);
        if (limit == null) {
            limit = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        List<AppRouteBo> appRouteBos = appRouteServiceLocal.getRootNodeByAppId(appId, role, limit, offset);
        Map<String,RouteBo> rootsResult = new HashMap<>();
        for (AppRouteBo appRouteBo : appRouteBos) {
            RouteBo rootNode = createTreeByRootNodeId(appRouteBo);
            rootsResult.put(appRouteBo.getRootIdentify(),rootNode);
        }
        return rootsResult;
    }

    @Override
    public RouteBo getRouteByRootIdentify(String appId, Integer role, String rootIdentify) {
//        AppRouteServiceLocalImplCheck.getRouteByRootIdentify(appId,role,routeIdentify);
        return createTreeByRootNodeId(appRouteServiceLocal.getSingleRootNode(appId, role, rootIdentify));
    }
    /**
     * @Description 构建Bo树
     *
     * @Param [node, routeBoHashMap]
     * @return void
     **/
    private void dfsCreateTree(RouteBo node, HashMap<String, RouteBo> routeBoHashMap) {
        if (node.getChildIds() != null) {
            String childs = node.getChildIds();
            String [] arrChilds = childs.split(ACDConfig.SPLIT_REGEX);
            List<RouteBo> childsBo = new ArrayList<>();
            for (String arrChild : arrChilds) {
                RouteBo child = routeBoHashMap.get(arrChild);
                if (child != null) {
                    childsBo.add(child);
                }
            }
            node.setChildNodes(childsBo);
        }
    }

    @Override
    public String editRoute(String appId, Integer role, String rootIdentify,RouteAo routeAo) {
//        //兜底方法：删除所有节点，再添加
//        RouteServiceLocalImplCheck.checkRoute(appId,role,rootIdentify,routeAo, appServiceLocal,queueBaseLocalService,propertyLocalService,appRouteServiceLocal);
//        //编辑路由
//        appRouteServiceLocal.deleteByAppIdAndRoleTrue(appId,role,rootIdentify);
//        return addRoutes(appId, role, rootIdentify, routeAo);
//        RouteServiceLocalImplCheck.checkRoute(appId, role, rootIdentify, routeAo, appServiceLocal, queueBaseLocalService, propertyLocalService, appRouteServiceLocal);
//        RouteServiceLocalImplCheck.checkRoute(appId, role, rootIdentify, routeAo, appServiceLocal, queueBaseLocalService, propertyLocalService, appRouteServiceLocal);
        if (routeAo.getNodeId() == null) {
            //根节点进行了修改，则直接把所有节点全删了更新更高效
            appRouteServiceLocal.deleteByAppIdAndRoleTrue(appId,role,rootIdentify);
            return addRoutes(appId, role, rootIdentify, routeAo);
        }
        //根节点没变的情况，递归找[孩子节点变了的节点]
        //将旧树构建成Ao对象
        RouteAo oldAoRouteTree = RouteConvert.convertBoToAo(getRouteByRootIdentify(appId, role, rootIdentify));
        //存储用来删除的nodeId集合
        List<String> needDeleteNodeIds = new ArrayList<>();
        //存储用来更新的实体类
        List<RouteEntity> needInsertNodes = new ArrayList<>();
        //当前的树 与 旧树做比较
        dfsCompareTwoTrees(oldAoRouteTree, routeAo, needDeleteNodeIds,needInsertNodes);
        routeDao.deleteInNodeIds(needDeleteNodeIds,routeAo.getNodeId());
        AppRouteBo singleRootNode = appRouteServiceLocal.getSingleRootNode(appId, role, rootIdentify);
        for (RouteEntity needInsertNode : needInsertNodes) {
            needInsertNode.setRootNodeId(singleRootNode.getRootNodeId());
            routeDao.insert(needInsertNode);
        }
        return rootIdentify;
    }

    /**
     * @Description 递归比较两棵树，将需要删除的旧节点存储在needDeleteNodeIds中，需要更新的对象放在needInsertNodes中
     *
     * @Param [oldAoRouteTree, newAoRouteTree, needDeleteNodeIds]
     * @return void
     **/
    private void dfsCompareTwoTrees(RouteAo oldAoRouteTree, RouteAo newAoRouteTree, List<String> needDeleteNodeIds, List<RouteEntity> needInsertNodes) {
        if (nodeChanged(oldAoRouteTree, newAoRouteTree)) {
            //这种情况，该节点没变，但是它的孩子节点出现了变化，[孩子节点添加、删除、更改] 将该节点树下的所有节点nodeId[自己的不加]加入删除集合
            List<RouteAo> childNodes = oldAoRouteTree.getChildNodes();
            for (RouteAo childNode : childNodes) {
                List<String> allNodeIds = getAllTreeNodeIds(childNode, new ArrayList<>());
                needDeleteNodeIds.addAll(allNodeIds);
            }
            StringBuffer stringBuffer = new StringBuffer();
            //更新该节点的childIds数据
            for (RouteAo childNode : newAoRouteTree.getChildNodes()) {
                Pair<RouteEntity, List<RouteEntity>> pair = RouteConvert.convertToEntities(childNode,false);
                stringBuffer.append(pair.getKey().getNodeId()).append(ACDConfig.SPLIT_REGEX);
                needInsertNodes.addAll(pair.getValue());
            }
            oldAoRouteTree.setChildIds(stringBuffer.toString());
        }
    }

    /**
     * @Description 传入一棵树，nodeIds存储该树下所有节点的nodeId
     *
     * @Param [oldAoRouteTree]
     * @return java.util.List<java.lang.String>
     **/
    private List<String> getAllTreeNodeIds(RouteAo oldAoRouteTree, List<String> nodeIds) {
        if (oldAoRouteTree != null && oldAoRouteTree.getNodeId() != null) {
            nodeIds.add(oldAoRouteTree.getNodeId());
            List<RouteAo> childNodes = oldAoRouteTree.getChildNodes();
            if ( childNodes != null) {
                for (RouteAo childNode : childNodes) {
                    getAllTreeNodeIds(childNode, nodeIds);
                }
            }
        }
        return nodeIds;
    }

    private boolean nodeChanged(RouteAo oldAoRouteTree, RouteAo newAoRouteTree) {
        Set<String> oldNodeIds = oldAoRouteTree.getChildNodes().stream().map(RouteAo::getNodeId).collect(Collectors.toSet());
        Set<String> newNodeIds = newAoRouteTree.getChildNodes().stream().map(RouteAo::getNodeId).collect(Collectors.toSet());
        //孩子节点改变了的情况 需要更新该节点的childIds，但该节点的uuid不要改变，否则会影响上游节点
        if (newNodeIds.contains(null) || oldNodeIds.size() != newNodeIds.size()) {
            return true;
        } else {
            //没改变，返回true
            return false;
        }
    }

    @Override
    public String deleteRoute(String appId, Integer role, String rootIdentify) {
        RouteServiceLocalImplCheck.deleteRoute(appId, role,rootIdentify,appServiceLocal);
        return appRouteServiceLocal.deleteByAppIdAndRoleTrue(appId, role, rootIdentify);
    }

    @Override
    public List<RouteBo> queryRouteByAppId(String appId) {
        RouteServiceLocalImplCheck.queryRouteByAppId(appId, appServiceLocal);
        List<RouteBo> routeBos = RouteConvert.convertToParams(routeDao.queryByAppId(appId));
        return routeBos;
    }

    @Override
    public List<RouteBo> queryRouteByQueueId(String appId, String queueId) {
//        RouteServiceLocalImplCheck.queryRouteByQueueId(appId,queueId, appServiceLocal,queueBaseLocalService);
        List<RouteBo> routeBos = RouteConvert.convertToParams(routeDao.queryByQueueId(queueId));
        return routeBos;
    }

    @Override
    public List<String> queryUsedPropertyValueByPropertyName(String appId, Integer role, String propertyName) {
        RouteServiceLocalImplCheck.queryUsedPropertyValueByPropertyName(appId, role, propertyName,appServiceLocal);
        List<AppRouteBo> rootNodes = appRouteServiceLocal.getAllRootNodeByAppId(appId, role);
        List<String> rootNodeIds = new ArrayList<>();
        for (AppRouteBo rootNode : rootNodes) {
            rootNodeIds.add(rootNode.getRootNodeId());
        }
        List<RouteEntity> routeEntityList = routeDao.queryUsedNodeByPropertyName(appId, rootNodeIds, propertyName);
        Set<String> resultValues = new HashSet<>();
        for (RouteEntity routeEntity : routeEntityList) {
            resultValues.addAll(Arrays.asList(routeEntity.getEnumValue().split(ACDConfig.SPLIT_REGEX)));
        }
        return new ArrayList<>(resultValues);
    }

    @Override
    public Map<String, Set<String>> queryAllUsedNode (String appId, Integer role,Integer nodeType) {
        RouteServiceLocalImplCheck.queryAllUsedNode(appId,role,nodeType,appServiceLocal);
        List<AppRouteBo> rootNodes = appRouteServiceLocal.getAllRootNodeByAppId(appId, role);
        List<String> rootNodeIds = new ArrayList<>();
        for (AppRouteBo rootNode : rootNodes) {
            rootNodeIds.add(rootNode.getRootNodeId());
        }
        Map<String, Set<String>> resultMap = new HashMap<>();
        List<RouteEntity> routeEntityList = routeDao.queryAllUsedNode(appId, rootNodeIds, nodeType);
        for (RouteEntity routeEntity : routeEntityList) {
            resultMap.getOrDefault(routeEntity.getPropertyName(), new HashSet<>())
                    .addAll(Arrays.asList(routeEntity.getEnumValue().split(ACDConfig.SPLIT_REGEX)));
        }
        return resultMap;
    }

    @Override
    public void deleteAllByAppId(String appId) {
        RouteServiceLocalImplCheck.deleteAllByAppId(appId,appServiceLocal);
//        List<RouteEntity> routeEntityList = routeDao.queryByAppId(appId);
//        for (RouteEntity routeEntity : routeEntityList) {
//            routeDao.deleteByNodeId(routeEntity.getNodeId());
//        }
        routeDao.deleteAllByAppId(appId);
    }



}
