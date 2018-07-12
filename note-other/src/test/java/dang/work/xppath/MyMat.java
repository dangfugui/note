package dang.work.xppath;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dangqihe on 2016/10/26.
 */
public class MyMat {
    @Test
    public void find() {
        Pattern pattern = Pattern.compile("(?<=)\\S+$");
        Matcher matcher = pattern.matcher("徐嵩 　xu123126 　天津大学");
        if (matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }

}
