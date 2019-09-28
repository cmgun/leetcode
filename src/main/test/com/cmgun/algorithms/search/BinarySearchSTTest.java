package com.cmgun.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchSTTest {

    @Test
    public void search() {
        BinarySearchST<Integer, Integer> st = new BinarySearchST<>(10);
        st.put(1, 11);
        st.put(3, 33);
        st.put(2, 22);
        Integer v1 = st.get(2);
        Assert.assertEquals(22, (int) v1);
        Integer v2 = st.get(4);
        Assert.assertNull(v2);
    }

}
