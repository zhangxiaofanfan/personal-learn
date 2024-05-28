package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-100-liked">环形链表</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 20:53:46
 */
public class HotCode025 {
    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
         if (head == null) {
             return false;
         }
         ListNode index1 = head, index2 = head;
         while (index1 != null && index2 != null) {
            index1 = index1.next;
            index2 = index2.next;
            if (index2 == null) {
                return false;
            }
            index2 = index2.next;
            if (index1 == index2) {
                return true;
            }
         }
         return false;
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
