package netease.smstest.util;

public enum ErrorMessageEnum {
    TEMPLATE_NOT_EXISTS(ResponseCode.RES_ENOTEXIST, "template id not exist"),

    SEND_ID_ERROR(ResponseCode.RES_ENOTEXIST,"no sendid"),
    SEND_NOT_EXISTS(ResponseCode.RES_ENOTEXIST,"no sendid"),
    NO_AUTHORIZATION(ResponseCode.RES_ENOTEXIST,"no authorization."),

    MOBILE_EMPTY(ResponseCode.RES_EPARAM,"mobiles cannot be null"),
    MOBILE_BLANK(ResponseCode.RES_EPARAM,"mobiles cannot be blank"),
    MOBILE_TOO_LANG(ResponseCode.RES_EPARAM,"mobile size overflow"),
    MOBILE_SIZE_ERROR(ResponseCode.RES_EPARAM,"mobile size 0"),
    VALID_MOBILE_SIZE_ERROR(ResponseCode.RES_EPARAM,"valid mobile size is 0"),


    OVER_FREQUENCY_MOBILES(ResponseCode.RES_EFREQUENTLY,"over frequency mobiles"),
    MOBILE_COUNTRY_ERROR(ResponseCode.RES_EPARAM,"unsupported send China,HK,Macao,Taiwan,international sms at the same request."),
    BUSINESS_ID_EMPTY(ResponseCode.RES_EPARAM,"businessId不能为空"),

    SIGNATURE_EMPTY(ResponseCode.RES_EPARAM,"signature cannot be null"),
    SIGN_TOO_LANG(ResponseCode.RES_EPARAM,"sms signature too long"),

    EXT_CODE_TOO_LANG(ResponseCode.RES_EPARAM,"sms extcode too long"),

    SMS_TYPE_ERROR(ResponseCode.RES_EPARAM,"invalid smsType"),
    SMS_TYPE_EMPTY(ResponseCode.RES_EPARAM,"smsType不能为空"),

    CONTENT_EMPTY(ResponseCode.RES_EPARAM,"content cannot be null"),
    CONTENT_TOO_LANG(ResponseCode.RES_EPARAM,"sms content too long"),
    CHANNEL_AUTH_ERROR(ResponseCode.RES_FORBIDDEN,"no channel sms authorization"),
    TEMPLATE_AUTH_ERROR(ResponseCode.RES_FORBIDDEN,"no template sms authorization"),
    MOBILES_FORMAT_ERROR(ResponseCode.RES_EPARAM,"mobiles' should be json format"),

    TEMPLATE_PARAM_EMPTY(ResponseCode.RES_EPARAM,"params cannot be null"),
    TEMPLATE_FORMAT_ERROR(ResponseCode.RES_EPARAM,"'params' should be json format"),

    BUSINESS_NOT_EXISTS(ResponseCode.RES_EPARAM,"business not exists"),

    CHANNEL_NOT_EXISTS (404,"找不到匹配通道"),
    CHANNEL_LIMITED (416,"提交到通道限流"),
    SMS_SUBMIT_ERROR (514,"提交到通道失败"),

    REPORT_SEND_RECORD_NOT_EXISTS (404,"找不到发送记录"),

    INTERNAL_SERVICE_ERROR(ResponseCode.RES_EUNKNOWN, "internal service error."),
    ;

    ErrorMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
