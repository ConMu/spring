package netease.test.utils;

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
}
