package com.cmgun.other;

/**
 * 静态变量，静态方法测试
 */
public class StaticTest {

    public static StaticTest single = new StaticTest();
    public static int c = 1;
    public static Integer b;

    private StaticTest() {
        System.out.println("----StaticTest()");
        c = 2;
        System.out.println("----StaticTest()-" + c);
//        b = 3;
    }
    public static StaticTest getInstance() {
        System.out.println("getInstance()-" + "c:" + c);
        return single;
    }

    /**
     * 静态对象，静态方法的调用，都不会调用到类的构造方法。
     */
    public static void main(String[] args) {
        System.out.println("main()");
        System.out.println("getInstance: " + getInstance().c);
        System.out.println("single: " + single.c);
        StaticTest dyObj = new StaticTest();
        System.out.println("dyObj: " + dyObj.c);
        System.out.println("end main()");

    }
}
