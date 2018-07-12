package my.java8.method;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Date Create in 2017/12/25
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Car car1 = Car.create(Car::new);
        List<Car> carList = Arrays.asList(car1);
        carList.forEach(Car::collide);
        carList.forEach(Car::repair);
        carList.forEach(car1::follow);
    }
}

class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

}