package com.dang.note.proxy.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Description:
 *
 * @Author dangfugui
 * @Date Create in 2017/11/21
 */
public class NettyMain {

    public static void main(String[] args) {
        //        // 服务启动类
        //        ServerBootstrap bootstrap = new ServerBootstrap();
        //        // Boss 线程监听端口和客户端分发
        //        ExecutorService boss = Executors.newCachedThreadPool();
        //        // Worker 线程负责处理客户端的读写
        //        ExecutorService worker = Executors.newCachedThreadPool();
        //        String a = "";
        //        a.intern()
        //
        //        // 设置NIO ServerSocketChannel 工厂
        //        // bootstrap(new NioServerSocketChannelFactory(boss, worker));

        String a = "aaa";
        String b = new String("aaa");
        System.out.println(a == b);
        System.out.println(b == b.intern());
        System.out.println(a == b.intern());
    }
}
