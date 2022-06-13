package netease.test.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 * acd路由节点表
 */
@Data
public class RouteEntity implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 路由首节点uuid
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
     * 属性标识
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
     * 子节点uuids组
     */
    private String childIds;

    /**
     * 创建时间(s)
     */
    private Timestamp dbUpdateTime;

    /**
     * 更新时间(s)
     */
    private Timestamp dbCreateTime;

    private static final long serialVersionUID = 1L;
}