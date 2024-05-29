package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/?envType=study-plan-v2&envId=top-100-liked">两两交换链表中的节点</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 23:10:55
 */
public class HotCode030 {
    public static void main(String[] args) {

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode();
        first.next = head;
        ListNode index = first, indexFront, indexRear;
        while (true) {
            indexFront = index.next;
            if (indexFront == null) break;
            indexRear = indexFront.next;
            if (indexRear == null) break;
            // 置换操作
            indexFront.next = indexRear.next;
            indexRear.next = indexFront;
            index.next = indexRear;

            index = indexFront;
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
