package com.zhangxiaofanfan.hotcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list/description/?envType=study-plan-v2&envId=top-100-liked">回文链表</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 20:40:52
 */
public class HotCode024 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        HotCode024 hotCode024 = new HotCode024();
        System.out.println(hotCode024.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
         if (head == null) {
             return true;
         }
         Deque<ListNode> deque = new ArrayDeque<>();
         ListNode temp = head;
         while (temp != null) {
             deque.addLast(temp);
             temp = temp.next;
         }
         while (deque.size() > 1) {
             if (deque.removeFirst().val != deque.removeLast().val) {
                 return false;
             }
         }
         return true;
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
