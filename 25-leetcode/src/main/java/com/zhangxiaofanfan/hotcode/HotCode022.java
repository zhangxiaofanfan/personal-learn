package com.zhangxiaofanfan.hotcode;


/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 15:22:42
 */
public class HotCode022 {
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode indexA = headA, indexB = headB;
        boolean listA = true, listB = true;
        while (indexA != null && indexB != null) {
            if (indexA == indexB) {
                return indexA;
            }
            indexA = indexA.next;
            indexB = indexB.next;
            if (indexA == null && listA) {
                indexA = headB;
                listA = false;
            }
            if (indexB == null && listB) {
                indexB = headA;
                listB = false;
            }
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
