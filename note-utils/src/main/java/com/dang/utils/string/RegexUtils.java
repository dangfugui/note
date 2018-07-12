package com.dang.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则 工具类
 * Created by duang on 2017/4/26.
 */
public class RegexUtils {

    public static List<String> regex(String regex, String content) {
        List<String> resultList = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        return resultList;
    }

    public static List<String> regexMore(String regex, String content) {
        List<String> resultList = new ArrayList<String>();
        if (regex == null || content == null) {
            return resultList;
        }
        int start = 0, end = 1000;
        if (regex.contains("@[")) {
            String param = regex.substring(regex.indexOf("@[") + 2, regex.length());
            regex = regex.substring(0, regex.indexOf("@["));
            try {
                String starts = param.substring(0, param.indexOf(":"));
                String ends = param.substring(param.indexOf(":") + 1, param.length() - 1);
                start = Integer.parseInt(starts);
                end = Integer.parseInt(ends);
            } catch (Exception e) {

            }
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int index = 0;
        while (matcher.find()) {
            if (index > end) {
                break;
            }
            if (index >= start) {
                resultList.add(matcher.group());
            }
            index++;
        }
        return resultList;
    }

    public static String regexToString(String regex, String content) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        StringBuffer res = new StringBuffer();
        while (matcher.find()) {
            res.append(matcher.group());
        }
        return res.toString();
    }
}
