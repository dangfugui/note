package com.dang.note;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by duang on 2017/12/1.
 */
public class JavaSwap {

    @Test
    public void testswap() throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1;
        Integer b = 2;
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int tmp = a;
        field.set(a, b);     // a = b
        field.set(b, tmp);   // b = tmp;
        System.out.println(a + "a:b" + b);

        Integer aa = 1000;
        Integer bb = 2000;
        Field field2 = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int tmp2 = aa;
        field.set(aa, bb);     // a = b
        field.set(bb, tmp2);   // b = tmp;
        System.out.println(aa + "aa:bb" + bb);
    }

    @Test
    public void testswap2() {
        int a = 1;
        int b = 2;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a + "a:b" + b);
        a = a + b - (b = a);
        System.out.println(a + "a:b" + b);
    }

    @Test
    public void testswap3() {
        int a = 123, b = 456;
        a = a * b;
        b = a / b;
        a = a / b;
        System.out.println(a + "a:b" + b);
        a = a * b / (b = a);
        System.out.println(a + "a:b" + b);
    }

    @Test
    public void testswap4() {
        int a = 1, b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + "a:b" + b);
        b ^= a ^ (a = b);
        System.out.println(a + "a:b" + b);
    }

}

