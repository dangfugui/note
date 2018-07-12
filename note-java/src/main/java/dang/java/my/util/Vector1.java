package com.dang.java.my.util;

import java.util.Vector;

public class Vector1 {
    public static void main(String[] args) {
        Vector v = new Vector();
        v.addElement(1);
        v.addElement(2);
        v.addElement("dang");
        System.out.println(v.toString());
        System.out.println("get(0):" + v.get(0));//u
    }
}
