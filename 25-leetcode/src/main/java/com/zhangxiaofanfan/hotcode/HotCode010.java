package com.zhangxiaofanfan.hotcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked">和为 K 的子数组</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 08:12:34
 */
public class HotCode010 {
    public static void main(String[] args) {

    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            int diff = sum - k;
            count += map.getOrDefault(diff, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
