package com.dang.java.my.offen;

import java.util.Scanner;

/*java.lang.
 * NullPointerException 空指针异常u
 * ClassCaseException 类型转换异常
 * ArithmeticException 算数异常
 * ArrayIndexOutOfBundsException小标越界 
 * InputMismatchException 输入不匹配异常
	//runTimeException   运行时异常  不需要声明 (throws)和捕获(try)
*/

public class MyException {
    public static void main(String[] args) {
        MyException exc = new MyException();
        exc.arithmetic();
        try {
            exc.throwout(1, 0);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void arithmetic() {
        Scanner scan = new Scanner(System.in);
        //		try {
        //			int a=scan.nextInt();
        //			System.out.println(5/a);
        //		} catch (ArithmeticException|InputMismatchException e) {
        //			System.out.println("算数异常rithmeticException");
        //			System.out.println("输入不匹配InputMismatchException");
        //		}finally{
        //			//可以做一些回收清理的事情
        //			System.out.println("MyException->arithmetic() end");
        //		}
    }

    public void throwout(int a, int b) throws MathException {
        if (b == 0) {
            throw new MathException("分母不能为0");
        }
        System.out.println(1 / 0);
    }

    class MathException extends Exception {
        public MathException(String mesage) {
            super(mesage);
        }
    }
}
