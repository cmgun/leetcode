package com.cmgun.leetcode.array;


/**
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Created by cmgun on 2019/12/12
 */
public class RemoveNthNodeFromEndofList {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
     * Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Remove Nth Node From End of List.
     * @param args
     */
    public static void main(String[] args) {
        // 1->2->3->4->5,n=2
        ListNode head = init(new int[]{1,2,3,4,5});
        ListNode result = removeNthFromEnd(head, 2);
        // 1,2,3,5
        printNodeList(result);

        // 1->2->3->4->5,n=0
        ListNode head1 = init(new int[]{1,2,3,4,5});
        ListNode result1 = removeNthFromEnd(head1, 0);
        // 1->2->3->4->5
        printNodeList(result1);

        // 1->2->3->4->5,n=5
        ListNode head2 = init(new int[]{1,2,3,4,5});
        ListNode result2 = removeNthFromEnd(head2, 5);
        // 2->3->4->5
        printNodeList(result2);
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
     * 记录当前节点的前第n个位置，找到队尾后操作倒数第n个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 边界处理
        if (n <= 0) {
            return head;
        }
        // 找到第n-1个节点位置，初始化跨步距离
        ListNode current = head;
        ListNode nPreNode = head;
        for (int i = 0; i < n; i++) {
            if (current == null) {
                // 超过边界，直接返回，不做修改
                return head;
            }
            current = current.next;
        }
        // 如果已经找到结尾，证明倒数第n个节点是头节点
        if (current == null) {
            head = head.next;
        } else {
            // 找到倒数第n个节点的前驱节点
            nPreNode = findNthPreNodeFromEnd(current, nPreNode);
            // 删除第n个节点
            nPreNode.next = nPreNode.next.next;
        }
        return head;
    }

    /**
     * 找到倒数第n个节点的前驱节点
     *
     * @param current 当前遍历的节点
     * @param nPreNode 倒数第n个节点的前1个节点
     */
    public static ListNode findNthPreNodeFromEnd(ListNode current, ListNode nPreNode) {
        if (current.next == null) {
            // 已经第n个节点的前驱节点
            return nPreNode;
        }
        nPreNode = nPreNode.next;
        current = current.next;
        return findNthPreNodeFromEnd(current, nPreNode);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
