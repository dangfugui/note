package my.thread.isrun;

/**
 * Description:
 *
 * @Date Create in 2018/2/28
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Boolean bool = true;
        IsRun isRun = new IsRun(bool);
        Consumer consumer = new Consumer();
        consumer.setIsRun(bool);
        Thread thread = new Thread(consumer);
        thread.start();
        System.out.println(bool.hashCode() + "============");
        Thread.sleep(10000);
        bool = false;
        System.out.println(bool.hashCode() + "-----------");
        Thread.sleep(10000);
    }
}
