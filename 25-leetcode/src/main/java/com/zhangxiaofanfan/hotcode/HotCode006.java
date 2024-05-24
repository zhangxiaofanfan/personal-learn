package com.zhangxiaofanfan.hotcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-22 08:10:29
 */
public class HotCode006 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        int index = 0, left = index + 1, right = nums.length - 1;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        while (index < nums.length - 2) {
            left = index + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(new ArrayList<>(List.of(nums[index], nums[left], nums[right])));
                    while (left < right && nums[left] == nums[++left]);
                    while (left < right && nums[right] == nums[--right]);
                } else if (sum < 0) {
                    while (left < right && nums[left] == nums[++left]);
                } else {
                    while (left < right && nums[right] == nums[--right]);
                }
            }
            while (index < nums.length - 2 && nums[index] == nums[++index]);
        }
        return res;
    }
}
