package my.java8.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date Create in 2017/12/26
 */
public class StreamsDemo {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        list.add(new Task(Status.OPEN, 5));
        list.add(new Task(Status.OPEN, 13));
        list.add(new Task(Status.CLOSED, 8));
        // 串行
        int sum = list.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoints).sum();
        System.out.println(sum);
        // 并行
        int totalPoints = list.stream().parallel().filter(task -> task.getStatus() == Status.OPEN).
                map(Task::getPoints).reduce(0, Integer::sum);
        System.out.println(totalPoints);

        Map<Status, List<Task>> map = list.stream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        final Collection<String> result = list
                .stream()                                        // Stream< String >
                .mapToInt(Task::getPoints)                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble(points -> (points / 6))   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%")      // Stream< String>
                .collect(Collectors.toList());                 // List< String >

        System.out.println(result);
    }

    private enum Status {
        OPEN, CLOSED
    }

    ;

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }
}
