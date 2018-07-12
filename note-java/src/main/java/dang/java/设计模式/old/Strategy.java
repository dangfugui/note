package com.dang.java.设计模式.old;

//可以比较
interface Comparable<E> {
    public int compareTo(E o);
}

//比较策略
interface Comparator<E> {
    int compare(E o1, E o2);
}

//策略模式//
public class Strategy {
    public static void main(String[] args) {
        int a[] = {9, 5, 2, 4, 8};
        Cat cats[] = {new Cat(5, 5), new Cat(2, 2), new Cat(6, 6)};
        Cat.comparator = new CatHeightCompartor();
        DataSorter.sort(cats);
        //Arrays.sort(cats);
        DataSorter.print(cats);
    }
}

class Cat implements Comparable<Cat> {
    public static Comparator<Cat> comparator;
    int height;
    int weight;

    public Cat(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return height + "|" + weight;
    }

    public int compareTo(Cat o) {
        return comparator.compare(this, o);
    }
}

class CatHeightCompartor implements Comparator<Cat> {

    public int compare(Cat o1, Cat o2) {
        Cat c1 = (Cat) o1;
        Cat c2 = (Cat) o2;
        if (c1.height > c2.height) {
            return 1;
        } else if (c1.height < c2.height) {
            return -1;
        } else {
            return 0;
        }
    }

}

class DataSorter {
    //冒择路（插入）兮（希尔）快归堆    七种常用排序
    public static void sort(Comparable[] a) {
        for (int i = a.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (a[j].compareTo(a[j + 1]) == 1) {
                    Comparable temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void print(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i].toString() + "  ");
        }
        System.out.println();
    }

}
