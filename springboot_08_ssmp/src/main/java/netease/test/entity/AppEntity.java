package netease.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:59
 * @since
 */
@Data
@TableName(value = "voip_acd_app")
public class AppEntity implements Serializable {

    /**
     * 自增ID
     */
//    @TableId(type = IdType.ASSIGN_ID)
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
    private Timestamp dbUpdateTime;

    /**
     * 更新时间(s)
     */
    private Timestamp dbCreateTime;

    private static final long serialVersionUID = 1L;
}