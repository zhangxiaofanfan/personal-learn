package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/description/?envType=study-plan-v2&envId=top-100-liked">搜索二维矩阵 II</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 08:34:03
 */
public class HotCode021 {
    public static void main(String[] args) {
        // 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
        // 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        int target = 20;
        HotCode021 hotCode021 = new HotCode021();
        System.out.println(hotCode021.searchMatrix(matrix, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }
        return Arrays.stream(matrix).anyMatch(array -> binarySearch(array, target));
    }

    public boolean binarySearch(int[] nums, int target) {
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return false;
        }
        int mid, left = 0, right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
