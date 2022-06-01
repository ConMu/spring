package com.conmu.domain;

import lombok.Data;

import java.util.List;

/**
 * 树测试
 *
 * @author mucongcong
 * @date 2022/05/31 10:39
 * @since
 **/
@Data
public class TreeNode {
    private String value; //对应数据库的ID
    private String text; //对应数据库的region_name
    private List<TreeNode> children;
    //省略get/set方法了
}
