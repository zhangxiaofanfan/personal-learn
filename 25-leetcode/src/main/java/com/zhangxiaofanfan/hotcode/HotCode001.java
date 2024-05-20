package com.zhangxiaofanfan.hotcode;


/**
 * <a href="https://leetcode.cn/problems/two-sum/description/?envType=study-plan-v2&envId=top-100-liked">两数之和</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-20 22:55:05
 */
public class HotCode001 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        for (int left = 0; left < nums.length; left++) {
            for (int right = left + 1; right < nums.length; right++) {
                if (target == nums[left] + nums[right]) {
                    return new int[] {left, right};
                }
            }
        }
        return null;
    }
}
