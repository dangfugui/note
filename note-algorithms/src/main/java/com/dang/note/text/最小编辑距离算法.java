package com.dang.note.text;

import java.io.IOException;

import com.dang.utils.io.FileUtil;
import com.dang.utils.mach.MathUtils;

/**
 * http://blog.csdn.net/ls386239766/article/details/38961745
 *
 * @Date Create in 2017/12/28
 */
public class 最小编辑距离算法 {

    public static void main(String[] args) throws IOException {
        System.out.println(compare("batyu", "beauty"));

        String jq = FileUtil.read("D:\\data\\新风会2 现场识别.txt");
        String tar = FileUtil.read("D:\\data\\新风会2 人工打标.txt");
        System.out.println(jq);
        System.out.println(tar);
        //        System.out.println(compare(jq, tar));
        //        System.out.println(compare(tar, jq));
        System.out.println(tar.length());
        String str = "问题解决掉这一块儿的话另外一个问题是一个问题后续的话我们作为一个安全部门这个问题非常核心就是政策和法律的探索和创新百度其实在这方"
                + "面已经做了很多工作我要强调就是在明年后年量产都是低速特别是明年的数受限什么样的速度什么样的限制使得无人驾驶可以安全高效的通用的广"
                + "告技术是没法完全解决必须跟法律痛苦我们在这方面有很多机会跟中国政府层面城市产品各方面做合作来推进北京市已经有了有了一个新的一个私"
                + "车的一个法规然后我们明天我们会跟雄安会有一个比较大的一个合作各方面的东西明天会但是这一系列都会仔细推荐因为推进政策的创新和落地是"
                + "我们大家一起的而且百度时有机会来引领跟政府合作来一起引领这个层面的推荐一个非常非常好的那么再问最后一个问题就结束今天的规划再问一"
                + "                + 我们大家一起的而且百度时有机会来引领跟政府合作来一起引领这个层面的推荐一个非常非常好的那么再问最后一个问题就结束今天的规划再问一"
                + "财务的目标是因为我们是一个上市公司或者一些核心的运营指标这样说明年手百dau_达到多少个可能因为财务的敏感性我们基本上把这些都不管下"
                + "单其他的原则是越透明越好核心原则就是因为我们是个上市公司财务报表比较敏感的一些目标数据我们就不讲了其它我认为我们公司应该越开越透"
                + "明越好这是这是我说的是对核心原则好那今天就到此为止时间那我们热烈感谢一下子好再次感谢大家今天的新闻会到此结束";
        System.out.println(str.length());
    }

    private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = MathUtils.min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

}
