package my.java8.util;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by duang on 2017/12/23.
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("python");
        list.add("php");
        list.add("JavaScript");
        System.out.println(list.stream().distinct().count());

        //        List<String> newList = list.stream().map(( V) -> )

        List<ShopingCart> carts = new ArrayList<>();
        carts.add(new ShopingCart("java", 80.0, 10));
        carts.add(new ShopingCart("python", 20.0, 20));
        carts.add(new ShopingCart("娃娃", 60.0, 2));
        carts.add(new ShopingCart("老师", 80.0, 10));
        carts.stream().map((sc) -> sc.getPrice() * sc.getAmount()).forEach(System.out::println);
        double totalPrice = carts.stream().map((sc) -> sc.getAmount() * sc.getPrice())
                .reduce((sum, m) -> sum + m).get();
        System.out.println("totalPrice:" + totalPrice);

        DoubleSummaryStatistics summaryStatistics = carts.stream().mapToDouble(
                (sc) -> sc.getPrice() * sc.getAmount()).summaryStatistics();
        System.out.println("商品个数" + summaryStatistics.getCount());
        System.out.println("最低花费" + summaryStatistics.getMin());
        System.out.println("最高花费" + summaryStatistics.getMax());
        System.out.println("平均花费" + summaryStatistics.getAverage());
        System.out.println("总消费" + summaryStatistics.getSum());

    }

}

class ShopingCart {
    String name;
    double price;
    int amount;

    public ShopingCart(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
