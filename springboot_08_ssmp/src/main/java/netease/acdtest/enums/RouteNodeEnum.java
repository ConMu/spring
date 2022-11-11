package netease.acdtest.enums;

/**
 * @author wangding02
 */

public enum RouteNodeEnum {

    /**
     * 属性名称节点
     */
    ROOT_NODE(0,"根节点"),
    ENUM_VALUE_NODE(1,"枚举值节点"),
    QUEUE_NODE(2,"队列节点");

    private RouteNodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
