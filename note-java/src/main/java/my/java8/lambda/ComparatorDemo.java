package my.java8.lambda;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date Create in 2017/12/26
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        Comparator<Integer> comparator = (a, b) -> a + b;  // lambda 表达式
        Map a = new HashMap();
    }

    static class MyLambda<T> {

        public void lambda(Comparator<T> comparator) {
            comparator.reversed();
        }
    }
}
