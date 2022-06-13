package netease.test.enums;

/**
 * app枚举类
 *
 * @author mucongcong
 * @date 2022/06/06 21:02
 * @since
 **/
public enum AppEnum {
    /**
     * 启用
     */
    App_Open(1,"启用"),
    /**
     * 启用
     */
    App_Close(0,"关闭");

    /**
     * 事件key
     */
    private Integer key;

    /**
     * 事件描述
     */
    private String des;

    private AppEnum(int key, String des) {
        this.key = key;
        this.des = des;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static String getDes(Integer key) {
        if (key == null) {
            return null;
        }
        for (AppEnum ivrType : AppEnum.values()) {
            if (ivrType.getKey().equals(key)) {
                return ivrType.getDes();
            }
        }
        return null;
    }

    public static String getAllDescription(){
        StringBuilder sb = new StringBuilder();
        for (AppEnum appEnum : AppEnum.values()) {
            sb.append(appEnum.getKey()).append(appEnum.getDes()).append(".");
        }
        return sb.toString();
    }
}
