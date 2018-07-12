package com.dang.my.idea;

import java.util.LinkedHashMap;

public class ReturnTest {

    public static void main(String[] args) {

        LinkedHashMap<String, String> map = new LinkedHashMap();
        map.put("a", "a");
        map.put("a", "a");
        map.remove("a");
        System.out.println(2 ^ 2);

    }

}
