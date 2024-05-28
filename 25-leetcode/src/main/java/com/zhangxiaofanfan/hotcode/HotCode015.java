package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 15:41:39
 */
public class HotCode015 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        HotCode015 hotCode015 = new HotCode015();
        hotCode015.rotate(nums, 8);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return ;
        }
        int[] newNums = new int[k];
        for (int i = 0; i < k; i++) {
            newNums[i] = nums[nums.length - k + i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            nums[nums.length - 1 - i] = nums[nums.length - 1 - k - i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = newNums[i];
        }
    }
}
