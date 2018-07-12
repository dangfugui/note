package com.dang.java.my.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 implements Runnable {
    ServerSocket serverSocket;

    @Override
    public void run() {
        // TODO 自动生成的方法存根u
        try {
            service();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void service() throws IOException {
        serverSocket = new ServerSocket(1588, 10); //端口1588  队列10
        System.out.println("serverScojet start====" + serverSocket);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();                        //主线程获取客户端连接
                Thread workThread = new Thread(new Handler(socket));    //创建线程
                workThread.start();                                    //启动线程
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
                serverSocket.close();
            }
        }

    }

    @Override
    protected void finalize() throws Throwable {
        // TODO 自动生成的方法存根
        super.finalize();
        serverSocket.close();
    }

}

class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("新连接:" + socket.getInetAddress() + ":" + socket.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.println("服务端接收到消息" + in.readLine());
            //out.println("欢迎访问");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("关闭连接:" + socket.getInetAddress() + ":" + socket.getPort());
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
