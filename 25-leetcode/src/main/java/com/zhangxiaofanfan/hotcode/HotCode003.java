package com.zhangxiaofanfan.hotcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked">最长连续序列</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-21 08:17:51
 */
public class HotCode003 {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        HotCode003 hotCode003 = new HotCode003();
        System.out.println(hotCode003.longestConsecutive(nums));

        // 输入：nums = [0,3,7,2,5,8,4,6,0,1]
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
