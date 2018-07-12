

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudongfeng on 17/7/17.
 */
public class ConvertAudioAnd {
    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream("toConvertData10.txt");// FileInputStream
            //            fis = new FileInputStream("/Users/wudongfeng/desktop/toConvertData10.txt");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            int count = 0;
            while ((str = br.readLine()) != null) {
                String[] strArray = str.split(" ");
                String source = strArray[0];
                String result = strArray[1];
                System.out.println("input:" + source);
                System.out.println("output:" + result);
                File file = new File(result);
                createPath(file.getParentFile());
                convert("ffmpeg", source, result);
                count++;
                //                str1 += str + "\n";
            }
            System.out.println("最终处理的数据个数为：" + count + " ====================================");
            // 当读取的一行不为空时,把读到的str的值赋给str1
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createPath(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            createPath(file.getParentFile());
        }
        file.mkdir();
    }

    public static void convert(String ffmpegUri, String originFileUri, String resultUri) {
        List<String> cmd = new ArrayList<String>();
        // ffmpeg -i in -vn -y -acodec copy out.aac 
        // ffmpeg -i tonghuazhen.mp3 -ar 1600 -acodec pcm_s16le  tonghuazhen3.wav
        cmd.clear();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        cmd.add("-vn");
        cmd.add("-y");
        cmd.add("-acodec");
        cmd.add("copy");
        cmd.add(resultUri);
        exec(cmd);

    }

    public static void exec(List<String> cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(); // 新建执行器
            builder.command(cmd);
            builder.redirectErrorStream(true);
            Process proc = builder.start();
            BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            proc.waitFor();
            stdout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}