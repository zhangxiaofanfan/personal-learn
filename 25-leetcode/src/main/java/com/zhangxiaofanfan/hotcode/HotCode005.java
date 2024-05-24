package com.zhangxiaofanfan.hotcode;

/**
 * <a href="https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked">盛最多水的容器</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-21 23:06:51
 */
public class HotCode005 {
    public static void main(String[] args) {

    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, area = 0;
        while (left < right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return area;
    }
}
