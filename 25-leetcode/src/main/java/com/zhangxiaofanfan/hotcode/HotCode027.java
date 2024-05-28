package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan-v2&envId=top-100-liked">合并两个有序链表</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 21:03:54
 */
public class HotCode027 {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode first = new ListNode();
        ListNode index1 = list1, index2 = list2, index = first;
        while (index1 != null && index2 != null) {
            if (index1.val <= index2.val) {
                index.next = index1;
                index1 = index1.next;
            } else {
                index.next = index2;
                index2 = index2.next;
            }
            index = index.next;
        }
        if (index1 != null) index.next = index1;
        if (index2 != null) index.next = index2;
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
