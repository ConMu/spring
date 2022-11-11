package netease.acdtest.utils;


import com.alibaba.fastjson.JSON;
import netease.acdtest.param.SeatInQueueParam;
import netease.acdtest.param.ao.RouteAo;
import java.util.*;

/**
 * @author mucongcong
 * @date 2022/06/14 15:37
 * @since
 **/
public class JsonGenerator {

//    static String[] role1PropertyNames = new String[]{"坐席属性","坐席渠道"};
//    static String[] role1Identifys = new String[]{"signerAttr","signerSource"};
//    static String[] enum1Value = new String[]{"全职座席;普通座席;普通坐席","APP;web;小程序;SDK;h5"};
//
//    static String[] role2PropertyNames = new String[]{"业务类型","客户渠道"};
//    static String[] role2Identifys = new String[]{"businessType","clientSource"};
//    static String[] enum2Value = new String[]{"车贷;房贷","APP;web;小程序;SDK;h5"};
//
//    static String[] queueIds = new String[]{"carLoan", "houseLoan","standbyQueue","testQueue1","testQueue2"};
//    static String[] queueNames = new String[]{"车贷队列", "房贷队列","备用队列","测试队列","测试队列2"};

    static String[] role1PropertyNames = new String[]{"坐席位置","坐席性别"};
    static String[] role1Identifys = new String[]{"Location","sex"};
    static String[] enum1Value = new String[]{"北京;上海;广州;深圳;杭州","男;女"};

    static String[] role2PropertyNames = new String[]{"客户位置","客户性别"};
    static String[] role2Identifys = new String[]{"Location2","sex2"};
    static String[] enum2Value = new String[]{"hz;gz;sh;sz;bj","nv;nan"};

    static String[] queueIds = new String[]{"ae8d384d80134a9889174bc22653c419", "9374eda0cbdb473aa3974872b3406396"};
    static String[] queueNames = new String[]{"queue1", "queue2"};

    public static void main(String[] args) {
        generateTreeNode();
//        generateSeatParams();
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

        //根节点
        RouteAo root_role1 = new RouteAo();
//        createFullTree(root_role1);
        createTestTree(root_role1);
        System.out.println(JSON.toJSONString(root_role1));
    }

    private static void createTestTree(RouteAo root_role1) {
        RouteAo node1_1_role1 = setRolePropertyChild(root_role1, 0, new int[]{0});
        RouteAo node2_1_role1 = setRolePropertyChild(node1_1_role1, 1, new int[]{0});
            RouteAo queue1_rol1 = setRoleQueueChild(node2_1_role1, 0);
        RouteAo node2_2_role1 = setRolePropertyChild(node1_1_role1, 1, new int[]{1});
            RouteAo queue2_rol1 = setRoleQueueChild(node2_2_role1, 1);

        RouteAo node1_2_role1 = setRolePropertyChild(root_role1, 0, new int[]{1,2});
        RouteAo node2_3_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{0});
            RouteAo queue3_rol1 = setRoleQueueChild(node2_3_role1, 0);
        RouteAo node2_4_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{1});
            RouteAo queue4_rol1 = setRoleQueueChild(node2_4_role1, 1);

        RouteAo node1_3_role1 = setRolePropertyChild(root_role1, 0, new int[]{3,4});
        RouteAo node2_5_role1 = setRolePropertyChild(node1_3_role1, 1, new int[]{0});
            RouteAo queue5_rol1 = setRoleQueueChild(node2_5_role1, 0);
        RouteAo node2_6_role1 = setRolePropertyChild(node1_3_role1, 1, new int[]{1});
            RouteAo queue6_rol1 = setRoleQueueChild(node2_6_role1, 1);

    }

    private static void createFullTree(RouteAo root_role1) {
        RouteAo node1_1_role1 = setRolePropertyChild(root_role1, 0, new int[]{0});
        RouteAo node2_1_role1 = setRolePropertyChild(node1_1_role1, 1, new int[]{0,1});
        RouteAo queue1_rol1 = setRoleQueueChild(node2_1_role1, 0);
        RouteAo queue2_rol1 = setRoleQueueChild(node2_1_role1, 1);
        RouteAo node2_2_role1 = setRolePropertyChild(node1_1_role1, 1, new int[]{2});
        RouteAo queue3_rol1 = setRoleQueueChild(node2_2_role1, 3);
        RouteAo node2_3_role1 = setRolePropertyChild(node1_1_role1, 1, new int[]{3,4});
        RouteAo queue4_rol1 = setRoleQueueChild(node2_3_role1, 4);

        RouteAo node1_2_role1 = setRolePropertyChild(root_role1, 0, new int[]{1});
        RouteAo node2_4_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{0});
        RouteAo queue5_rol1 = setRoleQueueChild(node2_4_role1, 0);
        RouteAo node2_5_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{1});
        RouteAo queue6_rol1 = setRoleQueueChild(node2_5_role1, 1);
        RouteAo node2_6_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{2,3});
        RouteAo queue7_rol1 = setRoleQueueChild(node2_6_role1,  2);
        RouteAo queue8_rol1 = setRoleQueueChild(node2_6_role1, 3);
        RouteAo node2_7_role1 = setRolePropertyChild(node1_2_role1, 1, new int[]{4});
        RouteAo queue9_rol1 = setRoleQueueChild(node2_7_role1, 4);
    }

    private static RouteAo setRoleQueueChild(RouteAo root,  int index) {
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

    private static RouteAo setRolePropertyChild(RouteAo root,  int propertyIndex, int[] enumIndex) {
        RouteAo node = new RouteAo();
        node.setPropertyIdentify(role1Identifys[propertyIndex]);
        node.setPropertyName(role1PropertyNames[propertyIndex]);
        List<String> enumValues = new ArrayList<>();
        String[] split = enum1Value[propertyIndex].split(";");
        for (int index : enumIndex) {
            enumValues.add(split[index]);
        }
        node.setEnumValue(enumValues);
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
