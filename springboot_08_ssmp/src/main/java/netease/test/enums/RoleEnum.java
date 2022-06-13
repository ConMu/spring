package netease.test.enums;

import java.util.Objects;

public enum RoleEnum {
    /***
     * seat
     */
    Role_Seat(1,"seat"),

    /***
     * client
     */
    Role_Client(2,"client");

    private Integer key;

    private String des;

    private RoleEnum(Integer key, String des) {
        this.key = key;
        this.des = des;
    }
    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static RoleEnum getRole(Integer role){
        if(Objects.isNull(role)){
            return null;
        }
        for (RoleEnum value : RoleEnum.values()) {
            if(Objects.nonNull(value)&&role.equals(value.getKey())){
                return value;
            }
        }
        return null;
    }
    public static String getDes(Integer key) {
        if (key == null) {
            return null;
        }
        for (RoleEnum ivrType : RoleEnum.values()) {
            if (ivrType.getKey().equals(key)) {
                return ivrType.getDes();
            }
        }
        return null;
    }

//    public static String getAllDescription(){
//        StringBuilder sb = new StringBuilder();
//        for (QueueStatusEnum queueStatusEnum : QueueStatusEnum.values()) {
//            sb.append(queueStatusEnum.getKey()).append(queueStatusEnum.getDes()).append(".");
//        }
//        return sb.toString();
//    }
}
