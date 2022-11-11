package netease.acdtest.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 * acd应用-路由首节点
 */
@Data
public class AppRouteEntity implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 路由标识
     */
    private String rootIdentify;

    /**
     * 应用id
     */
    private String appId;


    /**
     * 路由首节点uuid
     */
    private String rootNodeId;

    /**
     * 列表类型，1：角色1，2：角色2
     */
    private Integer role;

    /**
     * 是否启用
     */
    private Integer status;

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