package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked">合并 K 个升序链表</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-29 08:42:05
 */
public class HotCode034 {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int nullSize = 0, minIndex;
        ListNode first = new ListNode();
        ListNode index = first;
        while (nullSize < lists.length) {
            nullSize = 0;
            minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    nullSize++;
                } else {
                    minIndex = (minIndex == -1 || lists[i].val <= lists[minIndex].val) ? i : minIndex;
                }
            }
            if (minIndex == -1) {
                index.next = null;
            } else {
                index.next = lists[minIndex];
                lists[minIndex] = lists[minIndex].next;
                index = index.next;
            }
        }
        return first.next;
    }

    public class ListNode {
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
