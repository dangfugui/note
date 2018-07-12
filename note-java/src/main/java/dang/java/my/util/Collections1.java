package dang.java.my.util;

import java.util.Arrays;
import java.util.Comparator;

/*
 排序u
 */
public class Collections1 {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        String str = "8434831";
        char[] c = str.toCharArray();
        System.out.println(c);
        Arrays.sort(c);                            //排序
        int index = Arrays.binarySearch(c, '3');    //查找  之前必须调用sort
        System.out.println(index);
        System.out.println(c);

        String ss = "SDAFSDefsdfewfdsFEfdfsadfwefsdf";
        String[] s = {"dang", "Dang", "qihe", "d"};
        Arrays.sort(s, new myComparator());
        for (String string : s) {
            System.out.println(string);
        }
    }

}

/*
 * 自定义比较器
 * */
class myComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        // TODO 自动生成的方法存根
        String a = (String) o1;
        String b = (String) o2;
        if (a.length() < b.length()) {
            return 0;
        } else if (a.length() > b.length()) {
            return 1;
        } else {
            return a.compareTo(b);
        }

    }
}

/*
 * 可以直接进行排序
 * */
class MyComparable implements Comparable {
    int data;

    public MyComparable(int data) {
        this.data = data;
    }

    @Override
    public int compareTo(Object o) {
        // TODO 自动生成的方法存根
        MyComparable other = (MyComparable) o;
        return data - other.data;
    }

}
