package com.dang.note.proxy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Discards any incoming data.
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)是处理I/O操作的多线程事件循环。 Netty为不同类型的传输提供了各种EventLoopGroup实现
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b =
                    new ServerBootstrap(); // (2)是一个用于设置服务器的助手类。 您可以直接使用通道设置服务器。 但是，请注意，这是一个冗长的过程，在大多数情况下不需要这样做。
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)该类用于实例化新的通道以接受传入连接。
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)处理程序将始终由新接受的通道计算
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}