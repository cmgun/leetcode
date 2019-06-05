package com.cmgun.other;

import java.util.HashMap;

/**
 * 对象的引用测试
 */
public class ObjectRefTest {
    private int id;
    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public static HashMap<Integer, ObjectRefTest> map = new HashMap<>();

    public static void main(String[] args) {
        ObjectRefTest objectRefTest = new ObjectRefTest();
        objectRefTest.setId(1);
        map.put(1, objectRefTest);
        Integer a = 1;
        change(objectRefTest, a);
        System.out.println(objectRefTest.getId() + "_" + a + "_" + map.get(1).getId());
    }

    public static void change(ObjectRefTest objectRefTest, Integer a) {
        objectRefTest.setId(2);
        a = 2;
    }
}
