package com.dang.note.springboot.notespringboot.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dang.utils.io.FileUtil;

/**
 * @Date Create in 2018/1/15
 */
public class ReadLogTest {

    public static void main(String[] args) throws IOException {
        String path = "D:\\Linux\\识别数据\\优质课程\\度学堂长语音标注结果\\度学堂长语音标注结果\\第二批\\";
        List<String> fileList = new ArrayList<>();
        StringBuffer ans = new StringBuffer();
        String uuid = "def5800c-250c-44ca-a34a-e4b0388954c8".trim();
        int lean = 2700;
        for (int i = 0; i <= lean; i += 900) {
            // wk@audio_split2@17a425cb-a99b-4b72-8e7b-6e2b8b71fd75-0.mp3.TextGrid
            String fileName = "wk@audio_split2@" + uuid + "-" + i + ".mp3.TextGrid".trim();
            ans.append("\n" + uuid.substring(0, 6) + "_" + i + ":");
            List<String> list =
                    FileUtil.readAsList(path + fileName);
            list = list.stream().filter(s -> s.contains("text = \"")).collect(Collectors.toList());
            for (String str : list) {
                try {
                    String s = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\"")).trim();
                    ans.append(s);
                } catch (Exception e) {

                }
            }
        }
        String out = ans.toString();
        out = out.replaceAll("，|。| |？", "");
        out = out.replaceAll("<sil>", "");
        System.out.println(out);
    }
}
