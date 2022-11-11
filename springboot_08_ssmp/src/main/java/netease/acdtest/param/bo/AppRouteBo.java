package netease.acdtest.param.bo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * app-route出参
 *
 * @author mucongcong
 * @date 2022/06/06 10:50
 * @since
 **/
@Data
public class AppRouteBo implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 首节点标识
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
}
