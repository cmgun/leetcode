package com.cmgun.other;

/**
 * 静态变量，静态方法测试
 */
public class StaticTest {

    public static StaticTest single = new StaticTest();
    public static int c = 1;
    public static Integer b;
//    public static Integer b = 666;

    private StaticTest() {
        System.out.println("----StaticTest()-start-" + c);
        c = 2;
        System.out.println("----StaticTest()-" + c);
        b = 3;
    }
    public static StaticTest getInstance() {
        System.out.println("getInstance()-" + "c:" + c);
        return single;
    }

    /**
     * 静态对象，静态方法的调用，都不会调用到类的构造方法。
     * c最开始是0，然后被single赋值为2，跑完之后下一行c再赋值为1
     */
    public static void main(String[] args) {
        System.out.println("main()");
        System.out.println("getInstance: " + getInstance().c);
        System.out.println("single: " + single.c);
        System.out.println("c:" + c);
        StaticTest dyObj = new StaticTest();
        System.out.println("dyObj: " + dyObj.c);
        System.out.println("end main()");

    }
}
