package my.java8.iinterface;

import java.util.function.Supplier;

/**
 * @Date Create in 2017/12/25
 */
public interface Defaulable {

    /**
     * 在JVM中，默认方法的实现是非常高效的，并且通过字节码指令为方法调用提供了支持。默认方法允许继续使用现有的Java接口，
     * 而同时能够保障正常的编译过程。这方面好的例子是大量的方法被添加到java.util.Collection接口中去：stream()，parallelStream()，forEach()，removeIf()，……
     */
    default String df(String str) {
        System.out.println("Defaulable->df:" + str);
        return str;
    }

    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}

class DefaulableImpl implements Defaulable {

}

class OverridableImpl implements Defaulable {
    @Override
    public String df(String string) {
        return "Overridden implementation";
    }
}