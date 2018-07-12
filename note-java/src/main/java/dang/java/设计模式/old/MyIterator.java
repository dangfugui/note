package com.dang.java.设计模式.old;

interface Collection {
    Collection add(Object o);

    int size();

    Iterator iterator();
}

interface Iterator {
    Object next();

    boolean hasNext();
}

//迭代器//
public class MyIterator {
    public static void main(String[] args) {
        Collection al = new ArrayList();
        Collection ll = new LinkedList();
        for (int i = 0; i < 66; i++) {
            al.add(i);
            ll.add(i);
        }
        System.out.println("ArrayList->size()=" + al.size());
        System.out.println("LinkedList->size()=" + ll.size());
        Iterator iterator = ll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

//数组列表
class ArrayList implements Collection {
    Object[] objects = new Object[10];
    int index = 0;

    public ArrayList add(Object o) {
        if (index == objects.length) {
            Object[] newobject = new Object[objects.length * 2];
            System.arraycopy(objects, 0, newobject, 0, objects.length);
            objects = newobject;
        }
        objects[index++] = o;
        return this;
    }

    public int size() {
        return index;
    }

    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {
        private int currentIndex = 0;

        public Object next() {
            return objects[currentIndex++];
        }

        public boolean hasNext() {
            if (currentIndex >= size()) {
                return false;
            }
            return true;
        }

    }
}

//链表列表
class LinkedList implements Collection {
    Node head = null;//头结点
    Node tail = null;//尾节点
    int size = 0;

    public LinkedList add(Object o) {
        Node n = new Node(o, null);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
        return this;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator {
        Node now = head;

        public Object next() {
            Object result = now.o;
            now = now.next;
            return result;
        }

        public boolean hasNext() {
            if (now.next != null) {
                return true;
            }
            return false;
        }

    }
}

class Node {
    Object o;
    Node next;

    public Node(Object o, Node next) {
        this.o = o;
        this.next = next;
    }
}
