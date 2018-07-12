package com.dang.java.my.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoreServer {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8000);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        System.out.println("服务器已启动");
        while (true) {
            Socket socket = null;
            try {
                socket = ss.accept();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块u
                e.printStackTrace();
            }
            es.execute(new ClientThread(socket));
        }

    }
}

class ClientThread implements Runnable {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            System.out.println("客户端ip为：" + socket.getInetAddress());
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintStream ps = new PrintStream(out);
            ps.println("hello");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String info = br.readLine();
            System.out.println("客户端发送内容：" + info);
            out.close();
            in.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}
