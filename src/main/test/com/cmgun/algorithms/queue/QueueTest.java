package com.cmgun.algorithms.queue;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

    @Test
    public void test() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        Assert.assertEquals(queue.dequeue(), "a");
        Assert.assertEquals(queue.dequeue(), "b");
        Assert.assertFalse(queue.isEmpty());
        StringBuilder result = new StringBuilder();
        for (String element : queue) {
            result.append(element);
        }
        Assert.assertEquals(result.toString(), "cd");
    }
}
