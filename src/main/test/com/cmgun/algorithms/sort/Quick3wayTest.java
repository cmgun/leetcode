package com.cmgun.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class Quick3wayTest {

    @Test
    public void test() {
        Quick3way quick = new Quick3way();
        String result = quick.testSort(new String[]{"7","5","4","2","6","3","1", "4"});
        Assert.assertEquals(result, "1 2 3 4 4 5 6 7 ");
    }

}
