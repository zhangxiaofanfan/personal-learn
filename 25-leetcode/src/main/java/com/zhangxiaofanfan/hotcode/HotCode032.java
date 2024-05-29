package com.zhangxiaofanfan.hotcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-100-liked">随机链表的复制</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-29 08:21:50
 */
public class HotCode032 {
    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node first = new Node(0);
        Node indexOld = head, indexNew = first;
        while (indexOld != null) {
            indexNew.next = new Node(indexOld.val);
            map.put(indexOld, indexNew.next);
            indexNew = indexNew.next;
            indexOld = indexOld.next;
        }
        indexOld = head;
        indexNew = first.next;
        while (indexOld != null) {
            indexNew.random = map.get(indexOld.random);
            indexNew = indexNew.next;
            indexOld = indexOld.next;
        }
        return first.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
