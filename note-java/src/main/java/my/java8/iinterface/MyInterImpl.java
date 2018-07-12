package my.java8.iinterface;

/**
 * @Date Create in 2017/12/25
 */
public class MyInterImpl implements Defaulable {

    public static void main(String[] args) {
        MyInterImpl inter = new MyInterImpl();
        inter.df("hi");
        Defaulable defaulable = Defaulable.create(MyInterImpl::new);
        defaulable.df("hello");
    }

    public void println(String string) {
        df(string);
    }
}
