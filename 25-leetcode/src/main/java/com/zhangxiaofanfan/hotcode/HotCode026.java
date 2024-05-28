package com.zhangxiaofanfan.hotcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2&envId=top-100-liked">环形链表 II</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 20:57:56
 */
public class HotCode026 {
    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
         if (head == null) {
             return null;
         }
        ListNode temp = head;
        Set<ListNode> set = new HashSet<>();
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
