package com.dang.utils.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date Create in 2017/12/25
 */
public class LogReadUtils {

    public static List<String> readLog(String path, String grep) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(grep)) {
                list.add(line);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = null;
        try {
            list = readLog("D:\\data\\subtitle2.txt", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        for (String line : list) {
            if (line.length() > 2 && !line.contains(":") && !line.contains(".")) {
                sb.append(line.trim().replaceAll("。|，|,|？", ""));
            }

        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\data\\subtitle2out.txt");
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
