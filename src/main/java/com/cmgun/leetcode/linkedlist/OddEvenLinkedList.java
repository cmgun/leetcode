package com.cmgun.leetcode.linkedlist;


/**
 * 328. Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
 * <p>
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 * <p>
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * <p>
 * Created by cmgun on 2020/2/15
 */
public class OddEvenLinkedList {

    /**
     * Runtime: 0 ms
     * Memory Usage: 41.2 MB
     * @param args
     */
    public static void main(String[] args) {
        ListNode h1 = initNode(new int[]{1,2,3,4,5});
        printf(h1);
        printf(oddEvenList(h1));
        ListNode h2 = initNode(new int[]{2,1,3,5,6,4,7});
        printf(h2);
        printf(oddEvenList(h2));
        ListNode h3 = initNode(new int[]{1,2,3,4,5,6,7,8});
        printf(h3);
        printf(oddEvenList(h3));
    }

    /**
     * 从第二个节点位置开始存储偶数点位置，改变next指针；
     * 遍历结束后在原链表末尾加上偶节点链表
     */
    public static ListNode oddEvenList(ListNode head) {
        // 处理边界
        if (head == null || head.next == null) {
            return head;
        }
        // 偶节点链表位置
        ListNode evenListHead = new ListNode(0);
        ListNode evenList = evenListHead;
        // 从第二个节点开始
        ListNode current = head.next;
        ListNode preNode = head;
        ListNode lastOddNode = head;
        int index = 2;
        // 遍历链表
        while (current != null) {
            if (index % 2 == 0) {
                // 改变上个节点的next指针
                preNode.next = current.next;
                // 当前节点进入偶节点列表
                evenList.next = current;
                evenList = current;
            } else {
                // odd position
                lastOddNode = current;
            }
            preNode = current;
            current = current.next;
            index++;
        }
        // 偶节点后接
        lastOddNode.next = evenListHead.next;
        evenList.next = null;
        return head;
    }

    private static ListNode initNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode current = head;
        for (int i = 1; i < array.length; i++) {
            ListNode c = new ListNode(array[i]);
            current.next = c;
            current = c;
        }
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
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
