package dang.note.regex;

import org.junit.Test;

/**
 * Created by dangqihe on 2016/10/18.
 */
public class MyRegular {
    @Test
    public void test() {
        String str = "发布者: 天眼编辑05 | 来自: 天眼综合 | 发布时间: 2016-9-18 17:21\n" +
                "发布者: 天眼编辑 | 发布时间: 2016-9-18 18:22 | 评论: 3";
        String reg = "(?<=来自:.)\\w+(?=\\s)";
        System.out.println(str.replaceAll(reg, "$1"));
    }
}
