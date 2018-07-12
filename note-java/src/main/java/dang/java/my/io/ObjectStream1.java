package com.dang.java.my.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStream1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO自动生成的方法存根utf
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com.dang.java.my\\io\\file.txt";
        try {
            objectwriter = new ObjectOutputStream(new FileOutputStream(path));
            objectwriter.writeObject(new Student("gg", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));
            objectreader = new ObjectInputStream(new FileInputStream(path));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                objectreader.close();
                objectwriter.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }

    }

}

class Student implements Serializable {
    private String name;
    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

}

/*
读写对象：ObjectInputStream 和ObjectOutputStream
，该流允许读取或写入用户自定义的类，但是要实现这种功能，被读取和写入的类必须实现Serializable接口，其实该接口并没有什么方法，可能相当于一个标记而已，但是确实不合缺少的。实例代码如下：
*/
