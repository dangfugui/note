package com.dang.note.springboot.notespringboot.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogUtils {

    public static Queue<String> readLog(String path) throws IOException {
        Queue<String> queue = new LinkedBlockingQueue<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = null;

        //        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+".sub"));
        int lineNum = 0;
        while ((line = bufferedReader.readLine()) != null) {
            lineNum++;
            if (line.contains("{")) {
                String json = line.substring(line.indexOf("{")).trim();
                queue.add(json);
                System.out.println(json);
                //                bufferedWriter.write(line+"\n");
            }
        }
        return queue;
    }

    public static void main(String[] args) throws IOException {
        readLog("D:\\data\\peixun_live_chat.log");
    }

}
