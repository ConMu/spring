package netease.test.enums;

public enum AppRouteEnum {
    /**
     * 启用
     */
    AppRoute_Open(1,"启用"),
    /**
     * 启用
     */
    AppRoute_Close(0,"关闭");

    /**
     * 事件key
     */
    private Integer key;

    /**
     * 事件描述
     */
    private String des;

    private AppRouteEnum(int key, String des) {
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
        for (AppRouteEnum ivrType : AppRouteEnum.values()) {
            if (ivrType.getKey().equals(key)) {
                return ivrType.getDes();
            }
        }
        return null;
    }

    public static String getAllDescription(){
        StringBuilder sb = new StringBuilder();
        for (AppRouteEnum appRouteEnum : AppRouteEnum.values()) {
            sb.append(appRouteEnum.getKey()).append(appRouteEnum.getDes()).append(".");
        }
        return sb.toString();
    }
}
