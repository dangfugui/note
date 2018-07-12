package com.dang.java.my.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPClient {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        DatagramSocket ds;
        System.out.println("准备接收数据：");
        try {
            ds = new DatagramSocket(8090);
            ds.receive(dp);
            String info = new String(dp.getData(), 0, dp.getLength());
            System.out.println(info);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块u
            e.printStackTrace();
        }

    }
}
