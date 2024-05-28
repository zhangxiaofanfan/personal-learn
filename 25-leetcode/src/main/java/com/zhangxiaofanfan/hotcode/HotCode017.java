package com.zhangxiaofanfan.hotcode;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 17:07:19
 */
public class HotCode017 {
    public static void main(String[] args) {
        int[] nums = {9, 2, 3, 8};
        HotCode017 hotCode017 = new HotCode017();
        System.out.println(hotCode017.firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        int right = 0, index;
        boolean[] newNum = new boolean[nums.length];
        for (right = 0; right < nums.length; right++) {
            if (nums[right] > 0 && nums[right] <= nums.length) {
                newNum[nums[right] - 1] = true;
            }
        }
        for (index = 1; index <= nums.length; index++) {
            if (!newNum[index - 1]) {
                break;
            }
        }
        return index;
    }
}
