package com.dang.java.my.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStream1 {
    public static void main(String[] args) throws IOException {
        // TODO自动生成的方法存根
        byte[] buffer = new byte[512];   //一次取出的字节数大小,缓冲区大小
        int numberRead = 0;
        FileInputStream input = null;
        FileOutputStream out = null;
        input = new FileInputStream("c:/test/test.txt");
        out = new FileOutputStream("c:/test/test2.txt"); //如果文件不存在会自动创建
        while ((numberRead = input.read(buffer)) != -1) {  //numberRead的目的在于防止最后一次读取的字节小于buffer长度，
            out.write(buffer, 0, numberRead);       //否则会自动被填充0utf
        }
        input.close();
        out.close();
    }
}
/*

2.FileOutputStream 循序渐进版， InputStream是所有字节输出流的父类，子类有ByteArrayOutputStream,FileOutputStream,ObjectOutputStreanm,
这些我们在后面都会一一说到。先说FileOutputStream
我以一个文件复制程序来说，顺便演示一下缓存区的使用。(Java I/O默认是不缓冲流的，所谓“缓冲”就是先把从流中得到的一块字节序列暂存在一个被称为buffer
的内部字节数组里，然后你可以一下子取到这一整块的字节数据，没有缓冲的流只能一个字节一个字节读，效率孰高孰低一目了然。有两个特殊的输入流实现了缓冲功能，一个是我们常用的BufferedInputStream.)
 
*/
