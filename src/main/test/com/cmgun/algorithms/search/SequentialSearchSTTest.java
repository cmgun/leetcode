package com.cmgun.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class SequentialSearchSTTest {

    @Test
    public void search() {
        SequentialSearchST<Integer, Integer> st = new SequentialSearchST<>();
        st.put(1, 11);
        st.put(2, 22);
        st.put(3, 33);
        Integer v1 = st.get(2);
        Assert.assertEquals(22, (int) v1);
        Integer v2 = st.get(4);
        Assert.assertNull(v2);
    }

}
