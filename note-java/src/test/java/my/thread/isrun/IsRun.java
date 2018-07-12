package my.thread.isrun;

/**
 * Description:
 *
 * @Date Create in 2018/2/28
 */
public class IsRun {
    private Boolean isRun = false;

    public IsRun(Boolean isRun) {
        this.isRun = isRun;
    }

    public Boolean isRun() {
        return isRun;
    }

    public void setRun(Boolean run) {
        isRun = run;
    }
}
