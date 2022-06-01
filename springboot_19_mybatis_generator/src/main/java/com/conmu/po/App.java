package com.conmu.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * acd应用表
 */
@Data
public class App implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 是否启用
     */
    private Short status;

    /**
     * 创建时间(s)
     */
    private Date dbUpdateTime;

    /**
     * 更新时间(s)
     */
    private Date dbCreateTime;

    private static final long serialVersionUID = 1L;
}