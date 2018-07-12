package com.dang.note.proxy.netty.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeInt(6);
        output.writeBytes("ssssssss\r\ndddddddd");
        output.flush();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        int length = input.readInt();
        byte[] b = new byte[length];
        input.readFully(b, 0, length);
        System.out.println(new String(b));
        socket.close();
    }
}
