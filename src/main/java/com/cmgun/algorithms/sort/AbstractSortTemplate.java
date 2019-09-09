package com.cmgun.algorithms.sort;

/**
 * 抽象排序模板
 *
 * Created by cmgun on 2019/9/9
 */
public abstract class AbstractSortTemplate {

    /**
     * 算法名称
     *
     * @return
     */
    public abstract String name();

    /**
     * 排序方法
     *
     * @param a
     */
    public abstract void sort(Comparable[] a);

    /**
     * 元素比较
     *
     * @param v
     * @param w
     * @return
     */
    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 元素位置互换
     *
     * @param a
     * @param i
     * @param j
     */
    protected void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 元素展示
     *
     * @param a
     */
    protected String show(Comparable[] a) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            builder.append(a[i]).append(" ");
        }
        return builder.toString();
    }

    /**
     * 元素是否有序
     *
     * @param a
     * @return
     */
    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 输出排序结果
     *
     * @param a
     * @return
     */
    public String testSort(String[] a) {
        sort(a);
        if (isSorted(a)) {
            return show(a);
        }
        // 排序失败
        throw new RuntimeException("sort fail!");
    }
}
