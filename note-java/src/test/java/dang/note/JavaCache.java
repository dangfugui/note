package com.dang.note;

import org.junit.Test;

/**
 * Created by duang on 2017/12/1.
 */
public class JavaCache {

    @Test
    public void testCache() {
        Integer a = 1;
        Integer aa = new Integer(1);
        int aaa = 1;
        System.out.println(a == aa);
        System.out.println(a == aaa);
        System.out.println(aa == aaa);

        Integer b = 1000;
        Integer bb = new Integer(1000);
        int bbb = 1000;
        System.out.println(b == bb);
        System.out.println(b == bbb);
        System.out.println(bb == bbb);
    }

    @Test
    public void testCache2() {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        Integer aa = 1280;
        Integer bb = 1280;
        System.out.println(aa == bb);
    }
}

