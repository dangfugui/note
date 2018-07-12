package com.dang.note.proxy.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Description:
 *
 * @Author dangfugui@163.com
 * @Date Create in 2017/11/23
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap client = new Bootstrap();
        //  绑定线程组 处理读写和连接事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);
        //  绑定客户端通道
        client.channel(NioSocketChannel.class);
        // 给NioSocketChannel 初始化 handler
        client.handler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleClientHandler());
                ch.pipeline().addLast(new StringEncoder()); //  添加字符编码器
            }
        });
        ChannelFuture future = client.connect("localhost", 8080).sync();
        // 给服务器发送数据
        future.channel().writeAndFlush("hello netty");
        future.channel().closeFuture().sync();//    等待客户端关闭
        System.out.println("客户端关闭");

    }
}
