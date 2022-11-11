package netease.acdtest.utils;

import com.alibaba.druid.util.StringUtils;

/**
 * @author mucongcong
 * @date 2022/06/13 16:36
 * @since
 **/
public class TestString {
    public static void main(String[] args) {
        String s = "";
        System.out.println(s == null);
        System.out.println(s.isEmpty());
        System.out.println(StringUtils.isEmpty(s));
    }
}
