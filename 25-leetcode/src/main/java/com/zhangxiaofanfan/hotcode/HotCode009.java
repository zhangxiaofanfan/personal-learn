package com.zhangxiaofanfan.hotcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-23 08:30:19
 */
public class HotCode009 {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) {
            return list;
        }
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapP = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
            mapP.put(p.charAt(i), mapP.getOrDefault(p.charAt(i), 0) + 1);
        }
        if (isAnagrams(mapS, mapP)) {
            list.add(0);
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            // 把右侧数据添加到 map 中
            mapS.put(s.charAt(i), mapS.get(s.charAt(i)) - 1);
            if (mapS.get(s.charAt(i)) <= 0) {
                mapS.remove(s.charAt(i));
            }
            // 将左侧数据从 map 中删除
            int right = i + p.length();
            mapS.put(s.charAt(right), mapS.getOrDefault(s.charAt(right), 0) + 1);
            if (isAnagrams(mapS, mapP)) {
                list.add(i + 1);
            }
        }
        return list;
    }

    public boolean isAnagrams(Map<Character, Integer> mapS, Map<Character, Integer> mapP) {
        if (mapP.size() != mapS.size()) {
            return false;
        }
        return mapS.keySet().stream().allMatch(key -> Objects.equals(mapP.getOrDefault(key, -1), mapS.getOrDefault(key, -1)));
    }
}
