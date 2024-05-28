package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/rotate-image/description/?envType=study-plan-v2&envId=top-100-liked">旋转图像</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-27 20:56:12
 */
public class HotCode020 {
    public static void main(String[] args) {
        int[][] matrix = {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };
        HotCode020 hotCode020 = new HotCode020();
        Arrays.stream(matrix).forEach(array -> System.out.println(Arrays.toString(array)));
        hotCode020.rotate(matrix);
        System.out.println("---------------");
        Arrays.stream(matrix).forEach(array -> System.out.println(Arrays.toString(array)));
    }

    public void rotate(int[][] matrix) {
        int count = matrix.length / 2;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < matrix[i].length - i * 2 - 1; j++) {
                rotateOne(i, j, matrix);
            }
        }
    }

    public void rotateOne(int layer, int i, int[][] matrix) {
        int coordinate = matrix.length - 1 - layer, temp;
        temp = matrix[layer + i][coordinate];
        matrix[layer + i][coordinate] = matrix[layer][layer + i];
        matrix[layer][layer + i] = temp;

        temp = matrix[coordinate][coordinate - i];
        matrix[coordinate][coordinate - i] = matrix[layer][layer + i];
        matrix[layer][layer + i] = temp;

        temp = matrix[coordinate - i][layer];
        matrix[coordinate - i][layer] = matrix[layer][layer + i];
        matrix[layer][layer + i] = temp;
    }
}
