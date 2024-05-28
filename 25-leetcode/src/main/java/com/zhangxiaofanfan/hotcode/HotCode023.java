package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=top-100-liked">反转链表</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 20:36:28
 */
public class HotCode023 {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode();
        while (head != null) {
            ListNode temp = head.next;
            head.next = first.next;
            first.next = head;
            head = temp;
        }
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
