package dang.java.my.util;

import java.util.Hashtable;

public class Hashtable1 {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("name", "dang");//u
        hashtable.put(1, 110);
        System.out.println(hashtable.get(1));
        System.out.println(hashtable.get("name"));
        hashtable.clear();

    }
}
