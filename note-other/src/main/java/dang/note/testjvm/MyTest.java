package dang.note.testjvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dangqihe on 2016/10/11.
 */
public class MyTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(new int[128]);
            list.addAll(list);
            System.out.println(list.size());
        }
    }
}
