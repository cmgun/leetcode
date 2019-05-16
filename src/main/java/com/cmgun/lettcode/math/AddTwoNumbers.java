package com.cmgun.lettcode.math;

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Created by cmgun on 2019/5/16
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        addTwoNumbers(l1, l2);
        System.out.println(l1.val + "," + l1.next.val + "," + l1.next.next.val);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        ListNode q = new ListNode(0);
        p.next = l1;
        q.next = l2;
        // num1 add num2 is over than 10, the next bit will be add 1
        int carry = 0;
        // add l2 into l1
        while (p.next != null && q.next != null) {
            int sum = p.next.val + q.next.val + carry;
            // remove the carry bit
            p.next.val = q.next.val = sum % 10;
            carry = sum / 10;
            p = p.next;
            q = q.next;
        }
        // link the longer list
        if (p.next == null) {
             p.next = q.next;
        }
        // remain numbers
        while (p.next != null && carry != 0) {
            int sum = p.next.val + carry;
            p.next.val = sum % 10;
            carry = sum / 10;
            p = p.next;
        }
        // highest bit
        if (carry != 0) {
            p.next = new ListNode(1);
        }
        return l1;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
