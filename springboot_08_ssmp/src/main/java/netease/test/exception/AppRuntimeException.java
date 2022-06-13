package netease.test.exception;



import javax.servlet.http.HttpServletRequest;

/**
 * @author yine
 * @createTime 2021/4/20 3:20 下午
 * @description
 */
public class AppRuntimeException extends RuntimeException {
    private int errorCode;
    private String errorMessage;
//    private EnumInterface enumInterface;

    public AppRuntimeException() {
    }

    public AppRuntimeException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
//
//    public AppRuntimeException(EnumInterface errorEnum) {
//        super(errorEnum.getMsg(null));
//        this.errorCode = errorEnum.getCode();
//        this.errorMessage = errorEnum.getMsg(null);
//        this.enumInterface = errorEnum;
//    }
//
//    public AppRuntimeException(AppCode appCode) {
//        this(appCode.getCode(), appCode.getMessage());
//    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

//    public String getErrorMessageI18N(String language) {
//        //需要兼容的，，等修改完就不需要兼容了
//        if (enumInterface != null) {
//            return enumInterface.getMsg(language);
//        }
//        return errorMessage;
//    }
}
