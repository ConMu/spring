package netease.test.exception.code;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * WEB服务响应通用错误码定义表
 * @author yine
 * @createTime 2021/4/20 3:20 下午
 * @description
 */
public enum AppErrorCodeEnums {
    /**
     * wire [0,800)
     */
    // 成功
    OK(0, "OK"),//用于web
    SUCCESS(200, "SUCCESS"),//用于link
    PARAM_ERROR(400, "PARAM_ERROR"),
    AUTH_ERROR(401, "AUTH_ERROR"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR"),

    /**
     * base [800,1000)
     */
    // 未知错误
    UNKNOWN(800, "UNKNOWN"),
    // 没有权限
    NO_AUTH(801, "NO_AUTH"),
    // 数据库错误
    DB_ERROR(802, "DB_ERROR"),
    // 非法参数
    BAD_REQUEST(803, "BAD_REQUEST"),
    // 禁止操作
    FORBIDDEN(804, "FORBIDDEN"),
    // 操作被取消
    OP_CANCEL(805, "OP_CANCEL"),
    // 频繁
    FREQUENTLY(806, "FREQUENTLY"),
    // 失效
    INVALID(807, "INVALID"),
    // 目标不存在
    OBJECT_NOTEXIST(808, "OBJECT_NOTEXIST"),
    // 目标存在
    OBJECT_EXIST(809, "OBJECT_EXIST"),
    // 没有更新
    NOT_MODIFIED(810, "NOT_MODIFIED"),
    // 未就绪
    NOT_READY(811, "NOT_READY"),
    // 过期
    EXPIRED(812, "EXPIRED"),
    // 未完成
    NOT_DONE(813, "NOT_DONE"),
    // 已完成
    DONE(814, "DONE"),
    // 不足
    NOT_ENOUGH(815, "NOT_ENOUGH"),
    // 过量
    EXCESS(816, "EXCESS"),
    // 重复
    DUPLICATE(817, "DUPLICATE"),
    // 服务忙
    SERVICE_BUSY(818, "TOO_BUSY"),
    // 服务不可用
    SERVICE_DISABLE(819, "SERVICE_DISABLE"),
    // 服务不存在
    SERVICE_NULL(820, "SERVICE_NULL"),
    // 版本错误
    WRONG_VERSION(821, "WRONG_VERSION"),
    // 号码错误
    WRONG_NUMBER(822, "WRONG_NUMBER"),
    // 非法客户端类型
    WRONG_PRODUCT(823, "WRONG_PRODUCT"),
    // appid非法
    WRONG_APPID(824, "WRONG_APPID"),
    // appid存在
    APPID_EXIST(825, "APPID_EXIST"),
    // appid不存在
    APPID_UN_EXIST(826, "APPID_UN_EXIST"),
    // DID号码已被使用
    DIDNUMBER_EXIST(827, "DIDNUMBER_EXIST"),
    // 操作失败
    OP_FAILED(828, "OP_FAILED"),
    //企业不需要添加企业白名单
    CORP_BILLING_WHITE(829, "CORP_NOT_NEED_BILLING_WHITE"),
    //租户添加失败
    TENANT_ERROR(830, "TENANT_ERROR"),
    //租户名称为空
    TENANT_NULL(831, "TENANT_NULL"),
    //租户回调地址为空
    TENANT_URL_NULL(832, "TENANT_URL_NULL"),

    // 中继号没有外呼权限
    DID_NO_CALL_AUTH(833, "DID_NO_CALL_AUTH"),

    /**
     * 计费模块相关错误码
     */
    // 计费资源套餐包配置为空
//    OCS_COMBO_NULL(1001, "OCS_COMBO_NULL"),
//    //充值计算时长失败
//    OCS_CAL_FAILURE(1002, "OCS_CAL_FAILURE"),
//    OCS_OWE(1003,"CORP OWE"),
//    //企业欠费
//    OCS_DID_ACCOUNT(1004, "号码在使用，不能删除服务厂商"),
    // uuid_bridge


    //===========================原CtoCommonAppCode里迁移过来的==============================//

    //参数为空或者转json失败用这个吧
    PARAM_ILLEGAL(1000, "PARAM_ILLEGAL"),

    //================================账号体系相关错误Begin=========================================//

    //APP找不到
    APP_NOT_FOUND(1001, "APP_NOT_FOUND"),

    //app的号码不存在
    APP_PHONE_NOT_FOUND(1002, "APP_PHONE_NOT_FOUND"),

    //app传的DID号码非法
    APP_DIDNUMBER_ILLEGAL(1003, "APP_DIDNUMBER_ILLEGAL"),

    //企业欠费
    OCS_DID_ACCOUNT(1004, "号码在使用，不能删除服务厂商"),

    /**
     * 账号不存在
     */

    ACCOUNT_NOT_BIND(1100, "ACCOUNT_NOT_BIND"),

    //================================账号体系相关错误End=========================================//


    //================================基本通话状态等相关错误Begin=======================================//

    CALL_ID_NOTFOUND(2001, "CALL_ID_NOTFOUND"),

    CALL_TRANSFER_NOTCALLING(2100, "CALL_TRANSFER_NOTCALLING"),

    CALL_TRANSFER_FAILURE(2102 ,"CALL_TRANSFER_FAILURE"),

    //呼叫转坐席失败
    CALL_TO_SEAT_FAILURE(2103, "CALL_TO_SEAT_FAILURE"),

    ERROR_CALL_FORBID(2104, "ERROR_CALL_FORBID"),

    //================================基本通话状态等相关错误End=========================================//


    //================================会议状态等相关错误Begin=======================================//

    CTC_ID_NOTFOUND(2301, "CTC_ID_NOTFOUND"),

    CTC_NOTCALLING(2302, "CTC_NOTCALLING"),

    CTC_CREATE_FAILURE(2303 ,"CTC_CREATE_FAILURE"),

    CTC_PARAM_CHECK(2304, "CTC_PARAM_CHECK"),

    CTC_MEMBERID_NULL(2305, "CTC_MEMBERID_NULL"),

    CTC_CALL_TO_CONFERENCE_FAIL(2306, "CTC_CALL_TO_CONFERENCE_FAIL"),

    CTC_KICK_CONFERENCE_MEMBER_FAIL(2307 ,"CTC_KICK_CONFERENCE_MEMBER_FAIL"),

    CTC_MUTE_CONFERENCE_MEMBER_FAIL(2308, "CTC_MUTE_CONFERENCE_MEMBER_FAIL"),

    CTC_UNMUTE_CONFERENCE_MEMBER_FAIL(2309 ,"CTC_UNMUTE_CONFERENCE_MEMBER_FAIL"),

    CTC_MEMBER_CHANNEL_HANGUP_FAIL(2310, "CTC_MEMBER_CHANNEL_HANGUP_FAIL"),

    //================================基本通话状态等相关错误End=========================================//


    //================================队列等相关错误Begin=========================================//
    QUEUE_NOT_FOUND(3001, "QUEUE_NOT_FOUND"),

    QUEUE_PARAM_DUPLICATE(3002, "QUEUE_PARAM_DUPLICATE"),
    //队列满了
    QUEUE_FULL(3003, "QUEUE_FULL"),
    //队列已经存在
    QUEUE_EXIST(3004, "QUEUE_EXIST"),
    //顺振在队列中没有找到空闲的坐席
    QUEUE_TO_SEAT_NOT_FOUND(3005, "QUEUE_TO_SEAT_NOT_FOUND"),
    //顺振, 但坐席已经接通
    QUEUE_TO_SEAT_ANSWERED(3006, "QUEUE_TO_SEAT_ANSWERED"),

    //================================队列等相关错误End=========================================//


    //===============================坐席相关Begin==================================================

    SEAT_NOT_FOUND(4001, "SEAT_NOT_FOUND"),

    SEAT_OFF_LINE(4002, "SEAT_OFF_LINE"),

    SEAT_SETSTATE_STATE_ERROR(4003, "SEAT_SETSTATE_STATE_ERROR"),

    SEAT_LOCK_HAVE_CHANNEL(4004, "SEAT_LOCK_HAVE_CHANNEL"),

    SEAT_STATE_BUSY(4005, "SEAT_STATE_BUSY"),

    SEAT_STATE_SAME(4006, "SEAT_STATE_SAME"),

    SEAT_ANSWERTYPE_PHONE_INVALID(4007, "SEAT_ANSWERTYPE_PHONE_INVALID"),

    SEAT_LOCK_FAILURE(4008, "SEAT_LOCK_FAILURE"),

    SEAT_IN_CTC(4009, "SEAT_IN_CTC"),

    SEAT_STATE_READY_CALL(4010, "SEAT_STATE_READY_CALL"),


    /**
     * 坐席不在线
     */
    ERROR_OFFLINE(808, "ERROR_OFFLINE"),


    //===============================坐席相关End==================================================

    //===============================外呼相关Begin================================================
    CALL_OUT_ALL_REJECT(5001, "CALL_OUT_ALL_REJECT"),

    CALL_OUT_USER_REJECT(5002, "CALL_OUT_USER_REJECT"),
    //===============================外呼相关End==================================================

    /**
     * 未知错误
     */
    ERROR_UNKNOWN(9999, "ERROR_UNKNOWN"),

    //=======================================esls event=======================================

    /**
     * 事件类型是空的
     */
//    EVENT_TYPE_EMPTY(10001, "EVENT_TYPE_EMPTY"),
//
//    EVENT_ERROR_UNKNOWN(10002, "EVENT_ERROR_UNKNOWN"),

    SESSION_NOT_FOUND(10003, "SESSION_NOT_FOUND"),

    /**
     * 配置问题
     */
    ERROR_CONFIG(10004, "ERROR_CONFIG"),

    //=======================================esls event end====================================

    //================================属性等相关错误Begin=========================================//
    PROPERTY_IS_USED(11001,"PROPERTY_IS_USED"),
    //================================属性等相关错误Begin=========================================//
    RECORD_NOT_EXIST(12001,"RECORD_NOT_EXIST");
    private int code;
    private String message;

    AppErrorCodeEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private static final Map<Integer, AppErrorCodeEnums> lookup = new HashMap<>();

    static {
        for (AppErrorCodeEnums o : EnumSet.allOf(AppErrorCodeEnums.class)) {
            lookup.put(o.getCode(), o);
        }
    }

    public static AppErrorCodeEnums lookup(int code) {
        return lookup.get(code);
    }
}
