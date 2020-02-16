package com.cmgun.leetcode.linkedlist;


/**
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * Created by cmgun on 2020/2/16
 */
public class IntersectionofTwoLinkedLists {

    /**
     * Runtime: 1 ms
     * Memory Usage: 42.4 MB
     * @param args
     */
    public static void main(String[] args) {

        ListNode hc1 = initNode(new int[]{8,4,5}, null);
        ListNode ha1 = initNode(new int[]{4,1}, hc1);
        ListNode hb1 = initNode(new int[]{5,0,1}, hc1);

        printf(ha1);
        printf(hb1);
        ListNode result1 = getIntersectionNode(ha1, hb1);
        System.out.println(result1 != null ? result1.val : null);

        ListNode hc2 = initNode(new int[]{2,4}, null);
        ListNode ha2 = initNode(new int[]{0,9,1}, hc2);
        ListNode hb2 = initNode(new int[]{3}, hc2);
        ListNode result2 = getIntersectionNode(ha2, hb2);
        System.out.println(result2 != null ? result2.val : null);

        ListNode ha3 = initNode(new int[]{2,6,4}, null);
        ListNode hb3 = initNode(new int[]{1,5}, null);
        ListNode result3 = getIntersectionNode(ha3, hb3);
        System.out.println(result3);
    }

    /**
     * 使用两个指针遍历，首先指向两个链表开头。
     * 第一次遍历，调整两个链表不同长度的差异；第二次遍历，找到交际或null；
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界处理
        if (headA == null || headB == null) {
            return null;
        }
        // 两个指针
        ListNode a = headA;
        ListNode b = headB;
        // a == b，找到交际，或者null（没有交际）
        while (a != b) {
            // 如果为空，则两个链表长度不同，改变遍历的链表
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    private static ListNode initNode(int[] array, ListNode lastNode) {
        ListNode head = new ListNode(array[0]);
        ListNode current = head;
        for (int i = 1; i < array.length; i++) {
            ListNode c = new ListNode(array[i]);
            current.next = c;
            current = c;
        }
        current.next = lastNode;
        return head;
    }

    private static void printf(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println("");
    }

    public static class ListNode {
        Integer val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
