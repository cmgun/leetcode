package com.cmgun.other;

/**
 * 3.在未排序的数组中找到第 k 个最大的元素。示例：输入: [3,2,3,1,2,4,5,5,6] 和 k = 4 输出:4
 * <p>
 * Created by cmgun on 2020/2/5
 */
public class FindKthMax {

    public static void main(String[] args) {
        System.out.println(findKthMax(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    public static int findKthMax(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        k = nums.length + 1 - k;
        while (start < end) {
            //
            int pos = partition(nums, start, end);
            if (pos == k - 1) {
                // 找到第k大的位置
                break;
            } else if (pos < k - 1) {
                start = pos + 1;
            } else {
                end = pos - 1;
            }
        }
        return nums[k - 1];
    }

    public static int partition(int[] nums, int start, int end) {
        //小于区的下标
        int less = start - 1;
        //大于区的下标，默认以最后一个下标的数作为划分值
        int more = end;
        while (start < more) {
            if (nums[start] < nums[end]) {
                swap(nums, ++less, start++);
            } else if (nums[start] > nums[end]) {
                swap(nums, --more, start);
            } else {
                start++;
            }
        }
        swap(nums, more, end);
        // less为下一个分区点
        return less + 1;
    }

    /**
     * 交换
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
