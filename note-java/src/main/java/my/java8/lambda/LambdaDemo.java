package my.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * https://blog.csdn.net/yqj2065/article/details/49277699
 */
public class LambdaDemo {
    public static void main(String[] args) {

        System.out.println("10 + 5 = " + LambdaDemo.operate(10, 5, (Integer a, Integer b) -> a + b));
        System.out.println("10 - 5 = " + LambdaDemo.operate(10, 5, (a, b) -> a - b));
        System.out.println("10 x 5 = " + LambdaDemo.operate(10, 5, (Integer a, Integer b) -> {
            return a * b;
        }));
        System.out.println("10 / 5 = " + LambdaDemo.operate(10, 5, (Integer a, Integer b) -> a / b));

        System.out.println(LambdaDemo.sum(0, 10, e -> e * e));
    }

    //    interface MathOperation<T> {
    //        T operation(T a, T b);
    //    }
    private static <T> T operate(T a, T b, BiFunction<T, T, T> mathOperation) {
        return mathOperation.apply(a, b);
    }

    public static int sum(int start, int end, Function<Integer, Integer> function) {
        int sum = 0;
        for (Integer i = start; i <= end; i++) {
            sum += function.apply(i);
        }
        return sum;
    }

}
