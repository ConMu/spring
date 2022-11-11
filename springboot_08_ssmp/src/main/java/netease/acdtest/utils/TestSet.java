package netease.acdtest.utils;

import com.alibaba.druid.util.StringUtils;

/**
 * @author mucongcong
 * @date 2022/06/10 14:28
 * @since
 **/
public class TestSet {
    public static void main(String[] args) {
        String s = null;
        test(s);
        System.out.println(s);
    }

    private static void test(String s) {
        System.out.println(StringUtils.isEmpty(s));
        s = "a";
    }
}
