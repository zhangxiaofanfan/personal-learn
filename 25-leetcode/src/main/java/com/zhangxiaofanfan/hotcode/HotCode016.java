package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 16:37:24
 */
public class HotCode016 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        HotCode016 hotCode016 = new HotCode016();
        System.out.println(Arrays.toString(hotCode016.productExceptSelf(nums)));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            answer[i] = answer[i + 1] * nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] * nums[i];
        }
        answer[0] = answer[1];
        for (int i = 1; i < nums.length - 1; i++) {
            answer[i] = answer[i + 1] * nums[i - 1];
        }
        answer[answer.length - 1] = nums[nums.length - 2];
        return answer;
    }
}
