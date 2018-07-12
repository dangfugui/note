package com.dang.java.my.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStream1 {
    /**
     * 我们写一个检测文件长度的小程序，别看这个程序挺长的，你忽略try catch块后发现也就那么几行而已。
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com.dang.java.my\\io\\file.txt";
        int count = 0;  //统计文件字节长度
        InputStream inputStream = null;   //文件输入流
        inputStream = new FileInputStream(new File(path));
          /*1.new File()里面的文件地址也可以写成D:\\David\\Java\\java 高级进阶\\files\\tiger.jpg,前一个\是用来对后一个
           * 进行转换的，FileInputStream是有缓冲区的，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。
          */
        while (inputStream.read() != -1) {  //读取文件字节，并递增指针到下一个字节
            count++;
        }
        System.out.println("---长度是： " + count + " 字节");
        inputStream.close();

    }

}

/*
 我们一步一步来，首先，上面的程序存在问题是，每读取一个自己我都要去用到FileInputStream，我输出的结果是“---长度是： 64982
 字节”，那么进行了64982次操作！可能想象如果文件十分庞大，这样的操作肯定会出大问题，所以引出了缓冲区的概念。可以将inputStream.read()改成inputStream.read(byte[]b)
 此方法读取的字节数目等于字节数组的长度，读取的数据被存储在字节数组中，返回读取的字节数，InputStream还有其他方法mark,reset,markSupported方法，例如：
markSupported 判断该输入流能支持mark 和 reset 方法。
mark用于标记当前位置；在读取一定数量的数据(小于readlimit的数据)后使用reset可以回到mark标记的位置。
FileInputStream不支持mark/reset操作；BufferedInputStream支持此操作；
mark(readlimit)的含义是在当前位置作一个标记，制定可以重新读取的最大字节数，也就是说你如果标记后读取的字节数大于readlimit，你就再也回不到回来的位置了。
通常InputStream的read()返回-1后，说明到达文件尾，不能再读取。除非使用了mark/reset。2
 */
