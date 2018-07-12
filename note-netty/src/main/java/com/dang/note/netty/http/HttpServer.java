package com.dang.note.netty.http;

import java.util.HashMap;
import java.util.Map;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Description:
 *
 * @Date Create in 2018/2/8
 */
public class HttpServer {

    public static void main(String[] args) throws InterruptedException {
        HttpServer httpServer = new HttpServer();
        httpServer.bind();
        System.out.println("end");
    }

    private boolean keepAlive = true;
    private int bossGroupThread = 2;
    private int workerGroupThread = 20;
    private int port = 80;

    public void bind() throws InterruptedException {
        // 是处理I/O操作的多线程事件循环。 Netty为不同类型的传输提供了各种EventLoopGroup实现
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossGroupThread);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerGroupThread);
        // 是一个用于设置服务器的助手类。 您可以直接使用通道设置服务器。 但是，请注意，这是一个冗长的过程，在大多数情况下不需要这样做。
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup) // 该类用于实例化新的通道以接受传入连接。
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpRouterInitializer());
        Map<ChannelOption<?>, Object> channelOptions = channelOptions();
        for (ChannelOption option : channelOptions.keySet()) {
            b.option(option, channelOptions.get(option));
        }
        ChannelFuture f = b.bind(port).sync();// 启动客户端
        f.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    private Map<ChannelOption<?>, Object> channelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<>();
        // 设置的ServerChannel 服务端接受连接的队列长度，SO_BACKLOG：如果队列已满，客户端连接将被拒绝。默认值，Windows为200，其他为128。
        options.put(ChannelOption.SO_BACKLOG, 128);
        // 连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能。
        options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
        return options;
    }

    class HttpRouterInitializer extends ChannelInitializer<SocketChannel> {
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast("respDecoder-reqEncoder", new HttpServerCodec())
                    //把多个HTTP请求中的数据组装成一个，当服务器发送的response事先不知道响应的长度时就很有用
                    .addLast("http-aggregator", new HttpObjectAggregator(65536))
                    .addLast(new ChunkedWriteHandler())
                    .addLast("action-handler", new HttpServiceHandler());
        }
    }
}
