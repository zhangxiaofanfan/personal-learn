package com.zhangxiaofanfan.hotcode;


/**
 * <a href="https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked">移动零</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-21 21:21:26
 */
public class HotCode004 {
    public static void main(String[] args) {

    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < nums.length) nums[left++] = 0;
    }
}
