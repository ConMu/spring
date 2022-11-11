package netease.acdtest.utils;

import java.util.UUID;

/**
 * @author yine
 * @createTime 2021/4/20 4:18 下午
 * @description
 */
public class UUIDUtils {

    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String genOriUUID() {
        return UUID.randomUUID().toString();
    }

//    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("a","a");
//        map.put("b","b");
//        Json
//    }
}
