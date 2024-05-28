package com.zhangxiaofanfan.hotcode;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 10:34:02
 */
public class HotCode013 {
    public static void main(String[] args) {
        int[] nums = {1};
        HotCode013 hotCode013 = new HotCode013();
        System.out.println(hotCode013.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], minSum = Integer.MAX_VALUE, sum = 0;
        for (Integer num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum - Math.min(0, minSum));
            minSum = Math.min(minSum, sum);
        }
        return maxSum;
    }
}
