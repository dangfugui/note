package com.dang.java.设计模式.行为型模式.父类与子类;

public class 策略模式 { // strategy
    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();   // 根据new 出来的不同实例 来确定不同的策略
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}

interface ICalculator { // 首先统一接口
    public int calculate(String exp);
}

abstract class AbstractCalculator { // 辅助类
    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}

class Plus extends AbstractCalculator implements ICalculator {  // 三个实现类
    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}

class Minus extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "-");
        return arrayInt[0] - arrayInt[1];
    }
}

class Multiply extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\*");
        return arrayInt[0] * arrayInt[1];
    }
}