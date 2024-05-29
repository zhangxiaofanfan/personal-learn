package com.zhangxiaofanfan.hotcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-29 08:42:27
 */
public class HotCode035 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache {
    private final Deque<Integer> deque;
    private final Map<Integer, Integer> map;
    private final Integer capacity;

    public LRUCache(int capacity) {
        deque = new ArrayDeque<>();
        map= new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        deque.add(key);
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        int first;
        if (!map.containsKey(key) && capacity <= this.map.size()) {
            while (!map.containsKey(first = this.deque.removeFirst())) ;
            map.remove(first);
        }
        deque.addLast(key);
        map.put(key, value);
    }
}
