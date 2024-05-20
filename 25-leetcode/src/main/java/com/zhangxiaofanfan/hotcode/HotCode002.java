package com.zhangxiaofanfan.hotcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked">字母异位词分组</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-20 22:59:42
 */
public class HotCode002 {
    public static void main(String[] args) {
        String[] strs = {"bur", "rub"};
        HotCode002 hotCode002 = new HotCode002();
        hotCode002.groupAnagrams(strs).forEach(System.out::println);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String newKey = generateStr(str);
            map.computeIfAbsent(newKey, key -> new ArrayList<>()).add(str);
        }
        return map.values().stream().toList();
    }

    public String generateStr(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuffer stringBuffer = new StringBuffer();
        map.keySet().stream().sorted(Character::compareTo).forEach(key -> stringBuffer.append(key).append(map.get(key)));
        return stringBuffer.toString();
    }
}
