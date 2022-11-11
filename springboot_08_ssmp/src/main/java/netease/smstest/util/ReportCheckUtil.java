package netease.smstest.util;



import java.util.List;

/**
 * @author mucongcong
 * @date 2022/10/10 19:17
 * @since
 **/
public class ReportCheckUtil {

    public static <T> void checkCodeIsLegal(T param, String paramName, List<T> validParams) throws AppException {
        if (param == null) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }
        if (!validParams.contains(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is illegal!", paramName + " is illegal!");
        }
    }

    public static void pageCheck(Integer limit, Integer offset) throws AppException {
        if (limit == null) {
            limit = 20;
        }
        if (offset == null) {
            offset = 0;
        }
        if (limit < 0 || limit > 100) {
            throw new AppException(ResponseCode.RES_EPARAM, "limit is illegal");
        }
        if (offset < 0 || offset > 10000) {
            throw new AppException(ResponseCode.RES_EPARAM, "offset is illegal");
        }
    }
}
