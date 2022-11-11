package com.conmu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yine on 2016/12/30.
 */


public class ContextLogBean implements Serializable {

    private static final long serialVersionUID = 2391569511295262958L;

    private static final Logger logger = LoggerFactory.getLogger("testLog");

    private static ThreadLocal<ContextLogBean> logBean = new ThreadLocal<>();

    private static final String BEAN_INIT_FLAG = "BEAN_INIT_FLAG";

    private String path;

    private String ip;

    private int code;

    private long begintime;

    private long spendtime;

    private Object params;

    private Map<String, Object> props;

    private Object response;

    /**
     * 请求执行前调用
     */
    public static void before() {
        setIp("");
        setPath("");
        setCode(200);
        setBegintime(System.currentTimeMillis());
        setSpendtime(0L);
        setParams("");
        setResponse("");
        setProps(new HashMap<String, Object>());
        addProp(BEAN_INIT_FLAG, 1);
    }

    public static void init() {
        before();
    }

    public static boolean isInited() {
        if (getProp(BEAN_INIT_FLAG) != null) {
            return true;
        }

        return false;
    }

    public static void setIp(String ip) {
        get().ip = ip;
    }

    public static void setPath(String path) {
        get().path = path;
    }

    public static void setCode(int code) {
        get().code = code;
    }

    public static void setBegintime(long begintime) {
        get().begintime = begintime;
    }

    public static void setSpendtime(long spendtime) {
        get().spendtime = spendtime;
    }

    public static void setParams(Object params) {
        get().params = params;
    }

    public static void setResponse(Object response) {
        get().response = response;
    }

    public static void setProps(Map<String, Object> props) {
        get().props = props;
    }

    public static void addProp(String key, Object value) {
        if (getProps() != null) {
            get().props.put(key, (value == null ? "null" : value));
        }
    }

    public static void delProp(String key) {
        if (getProps() != null) {
            get().props.remove(key);
        }
    }

    public static Object getProp(String key) {
        if (getProps() == null) {
            return null;
        }
        return get().props.get(key);
    }

    public static String getIp() {
        return get().ip;
    }

    public static String getPath() {
        return get().path;
    }

    public static int getCode() {
        return get().code;
    }

    public static long getBegintime() {
        return get().begintime;
    }

    public static long getSpendtime() {
        return get().spendtime;
    }

    public static Object getParams() {
        return get().params;
    }

    public static Object getResponse() {
        return get().response;
    }

    public static Map<String, Object> getProps() {
        return get().props;
    }

    public static ContextLogBean get() {
        ContextLogBean lb = logBean.get();
        if (lb == null) {
            lb = new ContextLogBean();
            logBean.set(lb);
        }
        return lb;
    }

    public static ContextLogBean getAndClean() {
        delProp(BEAN_INIT_FLAG);
        return get();
    }

    /**
     * 返回执行后调用
     */
    public static void after() {
        setSpendtime(System.currentTimeMillis() - getBegintime());
        log();
    }

    public static void log() {
        logger.info(GsonUtils.getGson().toJson(getAndClean()));
    }

    public static String toJsonString(){
        return GsonUtils.getGson().toJson(getAndClean());
    }
}
