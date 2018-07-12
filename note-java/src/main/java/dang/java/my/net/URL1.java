package com.dang.java.my.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URL1 {
    public static void main(String[] args) {
        try {
            URL url =
                    new URL("http://img2.china-designer"
                            + ".com/exhibition/UploadNew/201407/water111108/20140726163905754754.jpg");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:\\testdown.jpg"));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.close();
            bis.close();
            System.out.println("文件下载完成");

        } catch (Exception e) {
            // TODO 自动生成的 catch 块u
            e.printStackTrace();
        }
    }
}
