package com.dang.note.other.permutation;

import java.util.Scanner;

public class MyMath {
    public static void main(String[] args) {
        MyMath myMath = new MyMath();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String ans = myMath.multiple(scan.nextLine());
            System.out.println(ans);
        }
        System.out.println();
    }

    private String str;

    //abs
    //log
    //pow
    /// <summary>
    /// 进行复杂计算
    /// </summary>
    /// <param name="str_now"></param>
    /// <returns>复杂计算结果u</returns>
    public String multiple(String str_now) {
        str = str_now;
        System.out.println(str);
        String sign = "";
        int at = 0;
        String[] signs = {"(", "^", "/", "*", "+", "-", "="};
        for (int i = 0; i < signs.length; i++) {
            int find = str.indexOf(signs[i]);
            if (find != -1) {
                at = find;
                sign = signs[i];
                break;
            }
        }
        switch (sign) {
            case "(": {
                int qian = 0, kuo2 = 0;
                for (int i = at; i < str.length(); i++) {
                    if (str.charAt(i) == '(') {
                        qian++;
                    }
                    if (str.charAt(i) == ')') {
                        qian--;
                        if (qian == 0) {
                            kuo2 = i;
                            break;
                        }
                    }
                }
                String s = multiple(str.substring(at + 1, kuo2 - at - 1));
                str = str_now.substring(0, at) + s + str_now.substring(kuo2 + 1, str_now.length() - kuo2 - 1);
                multiple(str);
                break;
            }
            case "^": {
                multiple(new Sign(str, "^").GetString());
                break;
            }
            case "/": {
                multiple(new Sign(str, "/").GetString());
                break;
            }
            case "*": {
                multiple(new Sign(str, "*").GetString());
                break;
            }
            case "+": {
                int sx = str.indexOf("-");
                if (sx > 0 && sx < at) {
                    at = sx;
                    sign = "-";
                    if (at < 1) {
                        break;
                    }
                    multiple(new Sign(str, "-").GetString());
                    break;
                }
                multiple(new Sign(str, "+").GetString());
                break;
            }
            case "-": {
                if (at < 1) {
                    break;
                }
                multiple(new Sign(str, "-").GetString());
                break;
            }
            case "=": {
                if (at < 1) {
                    break;
                }
                multiple(new Sign(str, "=").GetString());
                break;
            }
        }
        return str;
    }
}

class Sign {
    public int begin = 0, end, at;
    public String lift, right, sign, str;

    public Sign(String instr, String sign) {
        str = instr;
        this.sign = sign;
        end = str.length() - 1;
        at = instr.indexOf(sign);
        if (at != -1) {
            lift = instr.substring(0, at);
            right = instr.substring(at + 1, instr.length() - at - 1);
            for (int a = at - 1; a > 0; a--) {
                char c = instr.charAt(a);
                if (!((c >= '0' && c <= '9') || c == '.')) {
                    begin = a + 1;
                    lift = instr.substring(a + 1, at - a - 1);
                    break;
                }
            }
            for (int c = at + 1; c < instr.length(); c++) {
                char cc = instr.charAt(c);
                if (!((cc >= '0' && cc <= '9') || cc == '.')) {
                    end = c - 1;
                    right = instr.substring(at + sign.length(), c - at - sign.length());
                    break;
                }
            }
        }
    }

    /// <summary>
    /// 获取字符出局部答案
    /// </summary>
    /// <returns>局部答案</returns>
    public String GetAns() {
        double da = 0, db = 0;

        da = Double.parseDouble(this.lift);
        db = Double.valueOf(right);
        String ans = "";
        switch (this.sign) {
            case "+":
                ans = "" + (da + db);
                break;
            case "-":
                ans = "" + (da - db);
                break;
            case "*":
                ans = "" + (da * db);
                break;
            case "/":
                ans = "" + (da / db);
                break;
            case "^":
                ans = ("" + Math.pow(da, db));
                break;
            case "=":
                ans = " false";
                if (da == db) {
                    ans = "true";
                }
                break;
            case ">":
                ans = " false";
                if (da > db) {
                    ans = "true";
                }
                break;
            case "<":
                ans = " false";
                if (da < db) {
                    ans = "true";
                }
                break;
            case "!":
                ans = da + "-" + db;
                break;
        }
        return ans;
        // return new MyMath().counter(lift + sign + right);
    }

    /// <summary>
    /// 获得处理符号周边结果后的结果
    /// </summary>
    /// <returns></returns>
    public String GetString() {
        String ans = str.substring(0, begin) + this.GetAns() + str.substring(end + 1, str.length() - end - 1);
        return ans;
    }

}
