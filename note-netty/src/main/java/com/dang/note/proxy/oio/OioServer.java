package com.dang.note.proxy.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OioServer {
    public static int port = 8080;
    public static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务端启动完成");
        while (true) {
            // 获取一个套接字（阻塞）
            final Socket socket = serverSocket.accept();
            System.out.println("来一个新客户端");
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }); // 业务处理
        }
    }

    private static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        InputStream input = socket.getInputStream();
        int read;
        while ((read = input.read(bytes)) != -1) {  // 读取数据  （阻塞点）
            System.out.println(new String(bytes).trim());
            socket.getOutputStream().write("ok".getBytes());
        }
        input.close();
        socket.getOutputStream().close();
        System.out.println("断开连接");
    }
}
