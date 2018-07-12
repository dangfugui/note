package com.dang.java.my.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NioTimerServer {

    private ServerSocketChannel serverSocketChannel;    // 管道
    private Selector selector;      // 多路复用选择器
    private int port = 9090;

    public void listener(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);   //  设置成非阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);    // 绑定
        while (true) {
            // selector.selectNow();   // 没有请求的话 返回 null  永远不进行阻塞
            selector.select(1000); // 没有请求会阻塞 1000 毫秒
            // SelectionKey 包含了所有的请求状态
            Set<SelectionKey> keys = selector.selectedKeys();    // 获取准备就绪建集合
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                System.out.println("----------------------");
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    key.cancel(); //无效的key 取消掉
                }
                if (key.isAcceptable()) {
                    // 同意建立连接处处理
                    handAcceptable(key);
                } else if (key.isReadable()) {
                    // 读取处理
                    handRead(key);
                } else // 写入的处理
                    if (key.isWritable()) {
                        handWrite(key);
                    }
                iterator.remove();
            }

        }
    }

    private void handWrite(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        String current_Time = SimpleDateFormat.getDateTimeInstance().format(new Date());
        buffer.put(current_Time.getBytes("UTF-8"));
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        key.cancel();
    }

    private void handRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int size = socketChannel.read(buffer);
        if (size > 0) {
            buffer.flip();
            byte bytes[] = new byte[buffer.remaining()];
            buffer.get(bytes);
            String content = new String(bytes, "UTF-8");
            System.out.println("server receive date:" + content);
            buffer.clear();
            key.attach(content);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        }

    }

    private void handAcceptable(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        NioTimerServer server = new NioTimerServer();
        server.listener(9090);
    }
}
