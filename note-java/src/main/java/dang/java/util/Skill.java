package dang.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Description: 技能 java 语法糖
 *
 * @Author dangqihe
 * @Date Create in 2018/4/17
 */
public class Skill {

    /**
     * 判断对象链是否为空（a.b.c==null  a为空  则返回true 而不是报空指针）
     *
     * @param supplier
     *
     * @return
     */
    public static boolean isNull(Supplier supplier) {
        try {
            Object data = supplier.get();
            if (data == null) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param supplier
     *
     * @return
     */
    public static boolean isEmpty(Supplier supplier) {
        if (isNull(supplier)) {
            return true;
        }
        return isEmpty(supplier.get());
    }

    /**
     * 判断数据是否为空
     *
     * @return 是否为空
     *
     * @data 数据
     */
    public static boolean isEmpty(Object data) {
        if (data instanceof String) {   // String
            String str = (String) data;
            if (str.length() == 0) {
                return true;
            }
            return false;
        } else if (data instanceof Collection) {    //List  set
            Collection coll = (Collection) data;
            if (coll.size() == 0) {
                return true;
            }
            return false;
        } else if (data instanceof Map) {   // Map
            Map map = (Map) data;
            if (map.size() == 0) {
                return true;
            }
            return false;
        } else if (data.getClass().isArray()) { // Array
            Object[] array = (Object[]) data;
            if (array.length == 0) {
                return true;
            }
            return false;
        } else {
            return false;   // 不知道data 类型 不为null 即 是非空
        }
    }

    public static void main(String[] args) {
        System.out.println(isEmpty(() -> null));
        System.out.println(isEmpty(() -> ""));
        String[] array = new String[] {"0", "1"};
        System.out.println(isEmpty(() -> array[6]));
        System.out.println(isEmpty(() -> new ArrayList<>()));
        System.out.println(isEmpty(() -> new HashMap<>()));
        System.out.println(isEmpty(() -> new HashSet<>()));

        List<String> list = new ArrayList<>();
        list.add("dang");
        list.add("qi");
        Skill.forEach(() -> list, e -> System.out.println(e));
    }

    public static <T extends Iterable> void forEach(Supplier<T> supplier, Consumer consumer) {
        if (!isNull(supplier)) {
            T a = supplier.get();
            a.forEach(consumer);
        }
    }
}
