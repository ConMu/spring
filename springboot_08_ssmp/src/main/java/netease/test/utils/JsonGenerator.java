package netease.test.utils;


import com.alibaba.fastjson.JSON;
import netease.test.param.SeatInQueueParam;
import netease.test.param.ao.RouteAo;
import java.util.*;

/**
 * @author mucongcong
 * @date 2022/06/14 15:37
 * @since
 **/
public class JsonGenerator {
    private static final String appId = "abcdefg";
    public static void main(String[] args) {
//        generateTreeNode();
        generateSeatParams();
    }

    private static void generateSeatParams() {
        SeatInQueueParam seatInQueueParam = new SeatInQueueParam();
        seatInQueueParam.setAppId("neteaseBank");
        seatInQueueParam.setSeatId("adfada");
        //全职坐席，匹配两个队列
//        Map<String, String> attrMap = new HashMap<>();
//        attrMap.put("坐席渠道", "APP");
//        attrMap.put("坐席属性", "全职座席");
//        //单坐席，匹配一个车贷队列
//        attrMap.put("坐席渠道", "APP");
//        attrMap.put("坐席属性", "普通座席");
//        seatInQueueParam.setAttributeMap(attrMap);

        List<String> queueIds = new ArrayList<>();
////        queueIds.add("carLoan");
//        queueIds.add("houseLoan");
        queueIds.add("standbyQueue"); //备用队列
        seatInQueueParam.setQueueIds(queueIds);

        System.out.println(JSON.toJSONString(seatInQueueParam));
    }

    private static void generateTreeNode() {
        String[] role1PropertyNames = new String[]{"坐席属性","坐席渠道"};
        String[] role1Identifys = new String[]{"signerAttr","signerSource"};
        String[] enum1Value = new String[]{"全职座席;普通座席;普通坐席","APP;web;小程序;SDK;h5;TV"};

        String[] role2PropertyNames = new String[]{"业务类型","客户渠道"};
        String[] role2Identifys = new String[]{"businessType","clientSource"};
        String[] enum2Value = new String[]{"车贷;房贷","APP;web;小程序;SDK;h5"};

        String[] queueIds = new String[]{"carLoan", "houseLoan","standbyQueue"};
        String[] queueNames = new String[]{"车贷队列", "房贷队列","备用队列"};
        //根节点
        RouteAo root_role1 = new RouteAo();
//        root_role1.setNodeType(0);
        RouteAo node1_1_role1 = setRolePropertyChild(root_role1, role1PropertyNames, role1Identifys, enum1Value, 1, new int[]{0,1});
        RouteAo node2_1_role1 = setRolePropertyChild(node1_1_role1, role1PropertyNames, role1Identifys, enum1Value, 0, new int[]{0});
        RouteAo queue1_rol1 = setRoleQueueChild(node2_1_role1, queueIds, queueNames, 1);
        RouteAo queue2_rol1 = setRoleQueueChild(node2_1_role1, queueIds, queueNames, 0);
        RouteAo node2_2_role1 = setRolePropertyChild(node1_1_role1, role1PropertyNames, role1Identifys, enum1Value, 0, new int[]{1});
        RouteAo queue3_rol1 = setRoleQueueChild(node2_2_role1, queueIds, queueNames, 0);

        RouteAo node1_2_role1 = setRolePropertyChild(root_role1, role1PropertyNames, role1Identifys, enum1Value, 1, new int[]{2,3,4});
        RouteAo queue4_rol1 = setRoleQueueChild(node1_2_role1, queueIds, queueNames, 0);
        System.out.println(JSON.toJSONString(root_role1));

        RouteAo root_role2 = new RouteAo();
//        root_role2.setNodeType(0);
        RouteAo node1_1_role2 = setRolePropertyChild(root_role2, role2PropertyNames, role2Identifys, enum2Value, 1, new int[]{0,1});
        RouteAo node2_1_role2 = setRolePropertyChild(node1_1_role2, role2PropertyNames, role2Identifys, enum2Value, 0, new int[]{0});
        RouteAo queue1_rol2 = setRoleQueueChild(node2_1_role2, queueIds, queueNames, 0);
        RouteAo node2_2_role2 = setRolePropertyChild(node1_1_role2, role2PropertyNames, role2Identifys, enum2Value, 0, new int[]{1});
        RouteAo queue2_rol2 = setRoleQueueChild(node2_2_role2, queueIds, queueNames, 1);
        RouteAo node1_2_role2 = setRolePropertyChild(root_role2, role2PropertyNames, role2Identifys, enum2Value, 1, new int[]{2,3,4});
        RouteAo queue3_rol2 = setRoleQueueChild(node1_2_role2, queueIds, queueNames, 0);
        System.out.println(JSON.toJSONString(root_role2));

    }

    private static RouteAo setRoleQueueChild(RouteAo root, String[] queueIds, String[] queueNames, int index) {
        RouteAo node = new RouteAo();
        node.setQueueId(queueIds[index]);
        node.setQueueName(queueNames[index]);
        List<RouteAo> childNodes = root.getChildNodes();
        if (childNodes == null) {
            List<RouteAo> list = new ArrayList<>();
            list.add(node);
            root.setChildNodes(list);
        }else {
            childNodes.add(node);
        }
        return node;
    }

    private static RouteAo setRolePropertyChild(RouteAo root, String[] role1PropertyNames, String[] role1Identifys, String[] enum1Value, int propertyIndex, int[] enumIndex) {
        RouteAo node = new RouteAo();
        node.setPropertyIdentify(role1Identifys[propertyIndex]);
        node.setPropertyName(role1PropertyNames[propertyIndex]);
        StringBuffer sb = new StringBuffer();
        String[] split = enum1Value[propertyIndex].split(";");
        for (int index : enumIndex) {
            sb.append(split[index]).append(";");
        }
        node.setEnumValue(sb.toString());
        List<RouteAo> childNodes = root.getChildNodes();
        if (childNodes == null) {
            List<RouteAo> list = new ArrayList<>();
            list.add(node);
            root.setChildNodes(list);
        }else {
            childNodes.add(node);
        }
        return node;
    }
}
