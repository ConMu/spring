package netease.test.param.bo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 返回给前端的路由树节点
 *
 * @author mucongcong
 * @date 2022/06/01 17:28
 * @since
 **/
@Data
public class RouteBo implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 路由首节点id
     */
    private String rootNodeId;

    /**
     * 节点uuid
     */
    private String nodeId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 节点类型 0：根节点，2：[属性名称]&枚举值，3：队列
     */
    private Integer nodeType;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性类型
     */
    private String propertyIdentify;

    /**
     * 枚举数组
     */
    private String enumValue;

    /**
     * 路由到的队列标识
     */
    private String queueId;

    /**
     * 路由到的队列名称
     */
    private String queueName;

    /**
     * 子节点ids组，感觉有点冗余
     */
    private String childIds;

    /**
     * 子节点数组
     */
    private List<RouteBo> childNodes;

    /**
     * 创建时间(s)
     */
    private Timestamp dbUpdateTime;

    /**
     * 更新时间(s)
     */
    private Timestamp dbCreateTime;

}
