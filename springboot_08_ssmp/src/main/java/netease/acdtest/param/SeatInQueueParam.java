package netease.acdtest.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author dujun01
 * @date 2022/6/2  16:27
 * @since
 */
@Data
public class SeatInQueueParam implements Serializable {

    /**
     * {
     * "signerId": "",
     * "signerProperties": [
     * {
     * "priorityScore": 0,
     * "propertyMap": {}
     * }
     * ]
     * }
     */

    /**
     * 应用id
     */
    private String appId;

    /**
     * 客服的id,租户内唯一
     */
    private String seatId;

    /**
     * 客服指定入的队列id,指定技能组
     */
    private List<String> queueIds;

    /**
     * 客服指定入的队列name,指定技能组
     */
    private String queueName;

    /**
     * 属性list,根据list路由到对应的
     */
    Map<String, String> attributeMap;

    /**
     * 路由，不传走默认路由
     */
    private String routeIdentify;

}