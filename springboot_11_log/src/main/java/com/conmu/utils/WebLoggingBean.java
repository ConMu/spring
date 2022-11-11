package com.conmu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 云信for web
 *
 * @author jiangzhouyun@corp.netease.com
 */
public class WebLoggingBean {

    private static final Logger logger = LoggerFactory.getLogger("testLog");

    private static ThreadLocal<LoggingBean> logBean = new ThreadLocal<LoggingBean>();

    public WebLoggingBean() {

    }

    public static void init() {
        LoggingBean lb = LoggingBean.getInstance();
        logBean.set(lb);
        get().setProps(new HashMap<String, Object>());
        setCode(200);
        setAppid(0L);
        setUid(0L);
        setBegintime(0L);
        setSid(0, 0);
        setSpendtime(0L);
        setUserip("");
    }

    public static void setAppid(long appid) {
        get().setAppid(appid);
    }

    public static void setBegintime(long begintime) {
        get().setBegintime(begintime);
    }

    public static void setSpendtime(long spendtime) {
        get().setSpendtime(spendtime);
    }

    public static void setSid(int sid, int cid) {
        get().setSid(sid);
        get().setCid(cid);
    }

    public static void setCode(int code) {
        get().setCode(code);
    }

    public static void setUserip(String userip) {
        get().setUserip(userip);
    }

    public static void setUid(long uid) {
        get().setUid(uid);
    }

    public static void put(String key, Object value) {
        get().getProps().put(key, value);
    }

    public static LoggingBean get() {
        LoggingBean lb = logBean.get();
        if (lb == null) {
            lb = LoggingBean.getInstance();
            logBean.set(lb);
        }
        return lb;
    }

    public static void appendErrMsg(String k, String errMsg) {
        Object f = get().getProps().get("failList");
        JSONObject jo;
        if (f == null || StringUtils.isEmpty(f.toString())) {
            jo = new JSONObject();
        } else {
            jo = JSON.parseObject(f.toString());
        }
        jo.put(k, errMsg);
        put("failList", jo.toJSONString());
    }

    public static void appendErrMsg(String errMsg) {
        appendErrMsg("ErrMsg",  errMsg);
    }

    public static boolean isCodeSet() {
        return get().getCode() != 0;
    }

    /**
     * 日志打印，便于统计
     */
    public static void info() {
        //打印日志
        logger.info(WebLoggingBean.get().toString());
    }

    @JSONType(orders = {"appid", "uid", "sid", "cid", "code", "userip",
            "begintime", "spendtime", "props"})
    public static class LoggingBean {

        public static LoggingBean getInstance() {
            return new LoggingBean();
        }

        private long uid = 0;

        private int sid;

        private int cid;

        private long appid;

        private int code;

        private long begintime;

        private long spendtime;

        private String userip;

        private Map<String, Object> props;

        /**
         * @return the sid
         */
        public int getSid() {
            return sid;
        }

        /**
         * @param sid the sid to set
         */
        public void setSid(int sid) {
            this.sid = sid;
        }

        /**
         * @return the cid
         */
        public int getCid() {
            return cid;
        }

        /**
         * @param cid the cid to set
         */
        public void setCid(int cid) {
            this.cid = cid;
        }

        /**
         * @return the appid
         */
        public long getAppid() {
            return appid;
        }

        /**
         * @param appid the appid to set
         */
        public void setAppid(long appid) {
            this.appid = appid;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return the begintime
         */
        public long getBegintime() {
            return begintime;
        }

        /**
         * @param begintime the begintime to set
         */
        public void setBegintime(long begintime) {
            this.begintime = begintime;
        }

        /**
         * @return the spendtime
         */
        public long getSpendtime() {
            return spendtime;
        }

        /**
         * @param spendtime the spendtime to set
         */
        public void setSpendtime(long spendtime) {
            this.spendtime = spendtime;
        }

        /**
         * @return the props
         */
        public Map<String, Object> getProps() {
            return props;
        }

        /**
         * @param props the props to set
         */
        public void setProps(Map<String, Object> props) {
            this.props = props;
        }

        /**
         * @return the userip
         */
        public String getUserip() {
            return userip;
        }

        /**
         * @param userip the userip to set
         */
        public void setUserip(String userip) {
            this.userip = userip;
        }

        /**
         * @return the uid
         */
        public long getUid() {
            return uid;
        }

        /**
         * @param uid the uid to set
         */
        public void setUid(long uid) {
            this.uid = uid;
        }

        @Override
        public String toString() {
            if (uid == 0 && appid != 0) {
                uid = appid;//如果uid没有设置，并且appid不为0，则把uid设置为appid，方便日志查询
            }
            return JSON.toJSONString(this);
        }
    }

}
