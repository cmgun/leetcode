package com.cmgun.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class BinarySortTreeTest {

    @Test
    public void search() {
        BinarySortTree<Integer, Integer> st = new BinarySortTree<>();
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

    @Test
    public void sortKey() {
        BinarySortTree<Integer, Integer> st = new BinarySortTree<>();
        st.put(1, 11);
        st.put(6, 66);
        st.put(4, 44);
        st.put(3, 33);
        st.put(5, 55);
        st.put(2, 22);
        st.put(9, 99);
        Assert.assertEquals(1, (int) st.min());
        Assert.assertEquals(9, (int) st.max());
        Assert.assertEquals(6, (int) st.floor(6));
        Assert.assertEquals(6, (int) st.floor(8));
    }

}
