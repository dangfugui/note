package dang.note;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class MyPattern {
    @Test//以Java开头，任意字符结尾
    public void testMatches() {
        Pattern pattern = Pattern.compile("^Java.*");
        Matcher matcher = pattern.matcher("Java是一门编程语言");
        boolean bool = matcher.matches();
        System.out.println(bool);
    }

    //多条件分割字符
    @Test
    public void testSplit() {
        Pattern pattern = Pattern.compile("[, |]+");
        String[] strs = pattern.split("Java Hello World Java,Hello,,World|Sun");
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }

    //文字替换（首次出现字符）
    @Test
    public void testReplaceAll() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World ,正则表达式 Hello World");
        System.out.println(matcher.replaceFirst("Java"));
        System.out.println(matcher.replaceAll("Java"));
    }

    //文字置换
    @Test
    public void testAppendReplacement() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World ,正则表达式 Hello World");
        StringBuffer sbr = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sbr, "Java");
        }
        matcher.appendTail(sbr);
        System.out.println(sbr.toString());
    }

    //验证邮箱
    @Test
    public void testEmail() {
        String str = "dangqihe@163.com";
        Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }

    //去除html标记
    @Test
    public void testHtml() {
        Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
        String string = matcher.replaceAll("");
        System.out.println(string);
    }
}
