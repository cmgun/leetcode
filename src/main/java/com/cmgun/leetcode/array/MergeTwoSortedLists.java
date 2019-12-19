package com.cmgun.leetcode.array;

/**
 * 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Created by cmgun on 2019/12/17
 */
public class MergeTwoSortedLists {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
     * Memory Usage: 39.3 MB, less than 17.85% of Java online submissions for Merge Two Sorted Lists.
     * @param args
     */
    public static void main(String[] args) {
        // 1->2->4, 1->3->4
        ListNode l1 = init(new int[]{1,2,4});
        ListNode l2 = init(new int[]{1,3,4});
        ListNode result = mergeTwoLists(l1, l2);
        // 1->1->2->3->4->4
        printNodeList(result);
    }

    private static ListNode init(int[] vals) {
        ListNode head = null;
        ListNode preNode = null;
        for (int v : vals) {
            ListNode node = new ListNode(v);
            if (preNode != null) {
                preNode.next = node;
            } else {
                head = node;
            }
            preNode = node;
        }
        return head;
    }

    private static void printNodeList(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val + ",");
            tmp = tmp.next;
        }
        System.out.println("");
    }

    /**
     * 同时遍历两个数组，记录各自当前遍历的position
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界处理
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 初始化头节点
        ListNode head;
        ListNode result;
        if (l1.val < l2.val) {
            result = l1;
            l1 = l1.next;
        } else {
            result = l2;
            l2 = l2.next;
        }
        head = result;

        // 循环直到两个list都遍历结束
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }

        if (l1 != null) {
            result.next = l1;
        }
        if (l2 != null) {
            result.next = l2;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
