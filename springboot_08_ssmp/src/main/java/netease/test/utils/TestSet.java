package netease.test.utils;

import java.util.HashSet;

/**
 * @author mucongcong
 * @date 2022/06/10 14:28
 * @since
 **/
public class TestSet {
    public static void main(String[] args) {
        HashSet<String> objects = new HashSet<>();
        objects.add(null);
        System.out.println(objects.contains(null));

    }
}
