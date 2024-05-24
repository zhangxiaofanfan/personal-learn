package com.zhangxiaofanfan.hotcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-100-liked">无重复字符的最长子串</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-22 22:29:36
 */
public class HotCode008 {
    public static void main(String[] args) {
        String s = "abba";
        HotCode008 hotCode008 = new HotCode008();
        System.out.println(hotCode008.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int left = -1, right = 0, maxLength = s.isEmpty() ? 0 : 1;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            left = Math.max(left, map.getOrDefault(s.charAt(right), -1));
            map.put(s.charAt(right), right);
            maxLength = Math.max(maxLength, right - left);
            right++;
        }
        return maxLength;
    }
}
