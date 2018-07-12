package dang.tool.text;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by dangqihe on 2017/1/18.
 */
public class MyFileIO {
    private Set<String> set = new HashSet<String>();

    @Test
    public void removeRepetition() throws IOException {
        //读
        String filePath = "D:/phone.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        //写
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/phone2.csv"), "UTF-8"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (set.contains(line)) {
                continue;
            }
            set.add(line);
            line = line.replaceAll("[^\\d]+", "");
            if (line.length() > 18 || line.length() < 7) {
                continue;
            }
            bufferedWriter.write(line + "\r\n");
            System.out.println(line + ">>" + set.size());
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    // 选择合适数量的文本文档数据，写入txt。
    public void writerTxt(List<List> txtList) throws IOException {
        FileOutputStream fos = new FileOutputStream("E:/test.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//一行一行读取文件，解决读取中文字符时出现乱码
        BufferedWriter bw = new BufferedWriter(osw);
        for (int i = 0; i < txtList.size(); i++) {
            List tempList = new ArrayList<Integer>();
            tempList = txtList.get(i);
            int tempValue0 = (Integer) tempList.get(0);
            int tempValue1 = (Integer) tempList.get(1);
            if (tempValue0 < 5000 && tempValue1 < 5000) {
                bw.write(tempValue0 + " " + tempValue1 + "\r\n");
            }
        }
        bw.close();
        osw.close();
        fos.close();
    }

}
