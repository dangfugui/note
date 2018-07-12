package com.dang.java.my.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            System.out.println("连接成功：" + socket);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintStream ps = new PrintStream(out);
            ps.println("hello");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String info = br.readLine();
            System.out.println("服务器返回内容" + info);
            out.close();
            in.close();

        } catch (IOException e) {
            // TODO 自动生成的 catch 块u
            e.printStackTrace();
        }

    }
}
