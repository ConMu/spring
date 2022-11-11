package netease.smstest.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class BaseCheckUtil {

    /**
     * 检测不能为Empty参数
     *
     * @param param
     * @param paramName
     * @throws AppException
     */
    public static void checkParamIsEmpty(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }
    }

    /**
     * 检测参数长度
     *
     * @param param
     * @param paramName
     * @param maxlen
     * @throws AppException
     */
    public static void checkParamLen(String param, String paramName, Integer maxlen) throws AppException {
        if (param.length() > maxlen) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " length over:" + maxlen, paramName + " length over:" + maxlen);
        }
    }

    /**
     * 检测Boolean型参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static boolean checkBooleanParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        if (!param.equalsIgnoreCase("true") && !param.equalsIgnoreCase("false")) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not boolean", paramName + " not boolean");
        }

        return Boolean.parseBoolean(param);
    }

    /**
     * 检测Byte形参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static byte checkByteParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        try {
            return Byte.parseByte(param);
        } catch (NumberFormatException ex) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not byte", paramName + " not byte");
        }
    }

    /**
     * 检测Integer形参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static int checkIntegerParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException ex) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not integer", paramName + " not integer");
        }
    }

    /**
     * 检测Long形参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static long checkLongParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        try {
            return Long.parseLong(param);
        } catch (NumberFormatException ex) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not long", paramName + " not long");
        }
    }

    /**
     * 检测JsonObject参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static JSONObject checkJsonObjectParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        try {
            return JSONObject.parseObject(param);
        } catch (Exception ex) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not JsonObject", paramName + " not JsonObject");
        }
    }

    /**
     * 检测JsonArray参数
     *
     * @param param
     * @param paramName
     * @return
     * @throws AppException
     */
    public static JSONArray checkJsonArrayParam(String param, String paramName) throws AppException {
        if (StringUtils.isEmpty(param)) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " is empty!", paramName + " is empty!");
        }

        try {
            return JSONArray.parseArray(param);
        } catch (Exception ex) {
            throw new AppException(ResponseCode.RES_EPARAM, paramName + " not JsonArray", paramName + " not JsonArray");
        }
    }

    public static List<Integer> formatIntegers(String strInteger, String paramName) throws AppException {
        String[] strs = strInteger.split(",");
        List<Integer> results = new ArrayList<Integer>();

        for (String str: strs) {
            results.add(checkIntegerParam(str, paramName));
        }
        return results;
    }

    public static List<Long> formatLong(String strLong, String paramName) throws AppException {
        String[] strs = strLong.split(",");
        List<Long> results = new ArrayList<Long>();

        for (String str: strs) {
            results.add(checkLongParam(str, paramName));
        }
        return results;
    }
}
