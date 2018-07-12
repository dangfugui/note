package my.thread.isrun;

/**
 * Description:
 *
 * @Date Create in 2018/2/28
 */
public class Consumer implements Runnable {

    private Boolean isRun;

    public void setIsRun(Boolean isRun) {
        this.isRun = isRun;
        System.out.println(this.isRun.hashCode() + " :" + isRun.hashCode());
    }

    @Override
    public void run() {
        while (isRun) {
            System.out.println(isRun.hashCode());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("==============end==================");
    }
}
