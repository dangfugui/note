package com.dang.utils.mach;

/**
 * Description: 计算工具类
 *
 * @Date Create in 2017/12/28
 */
public class MathUtils {

    public static <T extends Comparable> T min(T... tlist) {
        if (tlist == null || tlist.length == 0) {
            return null;
        }
        T min = tlist[0];
        for (T t : tlist) {
            if (min.compareTo(t) > 0) {
                min = t;
            }
        }
        return min;
    }

    public static <T extends Comparable> T max(T... tlist) {
        if (tlist == null || tlist.length == 0) {
            return null;
        }
        T min = tlist[0];
        for (T t : tlist) {
            if (min.compareTo(t) < 0) {
                min = t;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(min(1, 2, 3));
        System.out.println(min(5, 2, 3));
    }
}
