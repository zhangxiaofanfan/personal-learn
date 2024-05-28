package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-100-liked">两数相加</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 22:47:32
 */
public class HotCode028 {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode first = new ListNode();
        ListNode index1 = l1, index2 = l2, index = first;
        int carry = 0, sum = 0;
        while (index1 != null || index2 != null) {
            sum = ((index1 == null) ? 0 : index1.val) + ((index2 == null) ? 0 : index2.val) + carry;
            carry = sum / 10;
            index.next = new ListNode(sum % 10);
            index = index.next;
            if (index1 != null) index1 = index1.next;
            if (index2 != null) index2 = index2.next;
        }
        if (carry > 0) {
            index.next = new ListNode(carry);
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
