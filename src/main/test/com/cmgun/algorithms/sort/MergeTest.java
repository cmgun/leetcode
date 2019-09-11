package com.cmgun.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class MergeTest {

    @Test
    public void test() {
        Merge merge = new Merge();
        String result = merge.testSort(new String[]{"7","5","4","2","6","3","1"});
        Assert.assertEquals(result, "1 2 3 4 5 6 7 ");
    }

}
