package com.dang.java.my.lang;

import java.util.Collection;
import java.util.Vector;

public class Generic {
    public static void main(String[] args) {
        Collection<String> c = new Vector();
        Collection d = new Vector<String>();
        Person<Integer> p = new Person();
        Person p2 = new Person(); //泛型的擦除u
        p.setItem(1);
        print(p);

    }

    /*
     * 通配符
     */
    public static void print(Person<?> p) {
        System.out.println(p.getItem());
    }
}

/**
 * T 的上限是 extends 的  即T必须也继承Pbject
 * super 设置下限
 *
 * @param <T>
 */
class Person<T> extends Object {
    private String name;
    private T item;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

}
