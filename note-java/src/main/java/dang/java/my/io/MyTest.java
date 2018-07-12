package com.dang.java.my.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class MyTest {
    private final String URL = "D:/Files/com.dang.java.my code/file test/myfile.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyTest myTest = new MyTest();
        //myTest.createFile();
        //myTest.outputStream();
        //myTest.inputStream();
        myTest.objectStream();

    }

    //创建文件
    public void createFile() throws IOException {
        File file = new File(URL);
        file.createNewFile();
        System.out.println("文件创建完毕");
    }

    ///out  文件的写入
    public void outputStream() throws IOException {
        String str = "dangqihe\n党启贺";
        OutputStream outputStream = new FileOutputStream(URL);
        outputStream.write(str.getBytes());
        System.out.println("文件outputStream 写入成功");
        outputStream.close();
    }

    //文件的读取utf
    public void inputStream() throws IOException {
        InputStream inputStream = new FileInputStream(URL);
        byte[] buffer = new byte[8];
        int len = 0;
        System.out.println("文件内容为：");
        while ((len = inputStream.read(buffer)) != -1) {
            System.out.print(new String(buffer, 0, len));
        }
        inputStream.close();
    }

    public void objectStream() throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(URL));
        objectOutputStream.writeObject(new Student1("dangqihe", 23));
        objectOutputStream.writeObject(new Student1("党启贺", 23));
        objectOutputStream.writeObject(new Student1("富贵", 23));
        System.out.println("类序列化完成");
        InputStream inputStream = new FileInputStream(URL);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Student1 student;
        objectInputStream.available();
        while ((student = (Student1) objectInputStream.readObject()) != null) {
            //student=(Student1) objectInputStream.readObject();
            System.out.println(student.toString());
        }
        objectOutputStream.close();
        objectInputStream.close();
    }

}

class Student1 implements Serializable {
    public String name;
    public int age;

    public Student1(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + ":" + age + "岁了";
    }
}
