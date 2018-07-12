package com.dang.note.proxy.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Description:
 *
 * @Author dangfugui@163.com
 * @Date Create in 2017/11/23
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();
        //  1绑定两个线程组分别来处理客户端通道的accept和读写事件
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        server.group(bossGroup, workerGroup);
        //  2绑定服务端通道 NioServerSocketChannel
        server.channel(NioServerSocketChannel.class);
        //  3给读写事件的线程通道绑定handler 去真正的处理读写
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                //                ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                // Unpooled
                //                        .wrappedBuffer(new byte[]{'\r','\n'})));//  以 \r , \n  为结尾分割的解码器
                // sssss\r\nddddd  >> 只接收了sssss
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));//   接收指定长度
                //  自己逻辑handler
                ch.pipeline().addLast(new SimpleServerHandler());
                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                ch.pipeline().addLast(new StringEncoder());
                //TailContext  解码的时候 是倒着来的  所以不能放在上面
            }
        });
        //  4监听端口
        ChannelFuture future = server.bind(8080).sync();
        future.channel().closeFuture().sync();  //  阻塞通道
    }
}

//  http://www.xuetuwuyou.com/course/198
