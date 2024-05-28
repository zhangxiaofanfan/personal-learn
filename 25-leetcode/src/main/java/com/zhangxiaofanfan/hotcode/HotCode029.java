package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-100-liked">删除链表的倒数第 N 个结点</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 22:55:33
 */
public class HotCode029 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        HotCode029 hotCode029 = new HotCode029();
        hotCode029.removeNthFromEnd(head, 5);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode();
        first.next = head;
        ListNode front = first, rear = head;
        while (rear != null && n > 1) {
            rear = rear.next;
            n--;
        }
        if (rear == null) {
            return first.next;
        }
        while (rear.next != null) {
            rear = rear.next;
            front = front.next;
        }
        front.next = front.next.next;
        return first.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
