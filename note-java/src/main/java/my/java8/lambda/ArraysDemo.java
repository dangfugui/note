package my.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @Date Create in 2017/12/25
 */
public class ArraysDemo {

    public static void main(String[] args) {
        Arrays.asList("a", "b", "c").forEach(e -> {
            System.out.println(e);
        });
        Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));

        List<String> list = Arrays.asList("4,12,6,23465,6".split(","));
        list.sort((e1, e2) -> e1.compareTo(e2));
        list.forEach(e -> System.out.println(e));

    }
}
