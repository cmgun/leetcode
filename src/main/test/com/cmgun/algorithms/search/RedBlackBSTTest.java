package com.cmgun.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class RedBlackBSTTest {
    @Test
    public void search() {
        RedBlackBST<Integer, Integer> st = new RedBlackBST<>();
        st.put(1, 11);
        st.put(6, 66);
        st.put(4, 44);
        st.put(3, 33);
        st.put(5, 55);
        st.put(2, 22);
        Integer v1 = st.get(2);
        Assert.assertEquals(22, (int) v1);
        Integer v2 = st.get(99);
        Assert.assertNull(v2);
        st.put(6, 999);
        Integer v3 = st.get(6);
        Assert.assertEquals(999, (int) v3);
    }
}
