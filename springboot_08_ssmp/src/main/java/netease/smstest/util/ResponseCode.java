package netease.smstest.util;

public final class ResponseCode {

    public static final String CODE = "code";

    public static final String DESC = "desc";

    /**
     * 通用错误码
     */
    public static final short RES_SUCCESS = 200;

    public static final short RES_EVERSION = 201; // 客户端版本不对,用于强制升级

    public static final short RES_ENOTINVITE = 300; // 用户没有被邀请

    public static final short RES_EBAN = 301; // 用户在黑名单中

    public static final short RES_EUIDPASS = 302; // 账号或密码错误

    public static final short RES_EUREG_EXIST = 303; // 要注册的帐号已存在

    public static final short RES_EUREG_NO_EXIST = 305; // 要注销的帐号不存在

    public static final short RES_EBATCH_NO_CHANGE = 304; // 增量接口，没有更改

    public static final short RES_ETOKEN = 306;//token 失效

    public static final short RES_ADDR_BLOCKADED = 310; // 登录IP或MAC被封锁

    public static final short RES_IP_NOT_ALLOWED = 315; // 内部帐户不允许在该地址登陆

    public static final short RES_UID_OR_PASS_ERROR = 316; // 用户名不存在或密码错误

    public static final short RES_ECLIENTVERSION = 317; // 客户端版本不匹配,用于协议升级和登录检查

    public static final short RES_DOMAINNOTEXIST = 320; // 域名不存在

    public static final short RES_DOMAIN_BAN = 321; // 域名被禁用

    public static final short RES_FORBIDDEN = 403; // 禁止操作

    public static final short RES_ENOTEXIST = 404; // 目标(对象或用户)不存在

    public static final short RES_NOT_MODIFIED = 406; // 数据自上次查询以来未发生变化（用于增量更新）

    public static final short RES_ETIMEOUT = 408; // 超时

    public static final short RES_EVERIFY = 413; // 验证失败

    public static final short RES_EPARAM = 414; // 客户端提交了非法参数

    public static final short RES_ECONNECTION = 415; // 网络连接出现问题

    public static final short RES_EFREQUENTLY = 416; // 操作太过频繁

    public static final short RES_EEXIST = 417; // 对象已经存在

    public static final short RES_EHTTP = 418; // http协议访问错误

    public static final short RES_ESIZE_LIMIT = 419; // 大小超过限制

    public static final short RES_EOP_EXCEPTION = 420; // 操作出现异常

    public static final short RES_OP_CANCEL = 421; // 权限被取消

    public static final short RES_ACCOUNT_BLOCK = 422; // 帐号被禁用

    public static final short RES_ACCOUNT_MUTE = 423; // 帐号被禁言

    public static final short RES_ACCOUNT_EXP = 430; // 帐号过期

    public static final short RES_ERR_DUP_NONCE = 431; // 重复的http请求，nonce同

    public static final short RES_ERR_ANTISPAM = 432; // 命中反垃圾，仅限内部接口、协议使用

    public static final short RES_RETRY_AGAIN = 449; // 请求需要重试，此次请求触发了分布式锁

    public static final short RES_EUNKNOWN = 500; // 不知道什么错误

    public static final short RES_EDB = 501; // 数据库操作失败

    public static final short RES_TOOBUZY = 503;// 服务器太忙

    public static final short RES_ENOTENOUGH = 507; // 不足

    public static final short RES_OVERDUE = 508; // 超过期限

    public static final short RES_INVALID = 509; // 已经失效

    public static final short RES_USER_NOT_EXIST = 510; // 用户不存在

    public static final short RES_APP_CANNOT_REACH = 511; // App 不可达

    public static final short RES_GATE_CANNOT_REACH = 512; // GATE 不可达

    public static final short RES_YIXIN_CANNOT_REACH = 513; // YIXIN 不可达

    public static final short RES_ESERVICE_UNAVAILABLE = 514; //服务不可用

    public static final short RES_BLACKHOLED = 599;       //协议被黑洞规则过滤

    public static final short RES_MOBILE_EBAN = 600; // 手机在黑名单中

    public static final short RES_ANTISPAM = 601; //

    public static final short RESP_CHANNEL_PRICE = 999; // 国际通道设置了价格，无法删除

}
