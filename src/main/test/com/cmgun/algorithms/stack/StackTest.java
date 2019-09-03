package com.cmgun.algorithms.stack;


import org.junit.Assert;
import org.junit.Test;

public class StackTest {


    @Test
    public void test() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        Assert.assertEquals(stack.pop(), "d");
        Assert.assertEquals(stack.pop(), "c");
        Assert.assertFalse(stack.isEmpty());
        StringBuilder result = new StringBuilder();
        for (String element : stack) {
            result.append(element);
        }
        Assert.assertEquals(result.toString(), "ba");
    }

}
