package com.dang.note.other.permutation;

//http://blog.csdn.net/hackbuteer1/article/details/6657435

/**
 * 递归实现全排列  recursion
 *
 * @author duang
 */
public class Permutation {
    public void start() {
        String str = "123456789";
        System.out.println(str + "的全排列为：");
        recursion(str, 0, str.length() - 1);

    }

    /**
     * 递归全排列u
     *
     * @param strin 字符串
     * @param begin 递归开始标记
     * @param end   结束标记
     */
    public void recursion(String strin, int begin, int end) {
        char[] str = strin.toCharArray();
        if (begin == end) {
            System.out.println(strin);
        } else {
            for (int i = begin; i <= end; i++) {
                char swap = str[i];
                str[i] = str[begin];
                str[begin] = swap;
                recursion(new String(str), begin + 1, end);
            }

        }
    }

    public static void main(String[] args) {
        new Permutation().start();
    }
}
