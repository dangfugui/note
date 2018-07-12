package com.dang.java.my.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        String info = "hello";
        byte[] bytes = info.getBytes();
        try {
            DatagramPacket dp =
                    new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("127.0.0.1"), 8090);
            DatagramSocket ds = new DatagramSocket(9000);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
