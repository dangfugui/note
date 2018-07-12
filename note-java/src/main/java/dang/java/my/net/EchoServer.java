package com.dang.java.my.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("服务器已启动，正在等待连接");
            Socket socket = serverSocket.accept();//等待连接，该方法会阻塞u
            InputStream input = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String info = br.readLine();
            System.out.println(info);

            OutputStream output = socket.getOutputStream();
            PrintStream ps = new PrintStream(output);
            ps.println("echo:" + info);
            output.close();
            input.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }
}
