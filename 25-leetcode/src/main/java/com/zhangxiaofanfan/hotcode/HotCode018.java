package com.zhangxiaofanfan.hotcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-100-liked">矩阵置零</a>
 *
 * @author zhangxiaofanfan
 * @date 2024-05-27 20:35:50
 */
public class HotCode018 {
    public static void main(String[] args) {
        HotCode018 hotCode018 = new HotCode018();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        hotCode018.setZeroes(matrix);
        Arrays.stream(matrix).forEach(array -> System.out.println(Arrays.toString(array)));
    }

    public void setZeroes(int[][] matrix) {
        // 表示行数
        Set<Integer> rowSet = new HashSet<>();
        // 表示列数
        Set<Integer> colSet = new HashSet<>();
        int rowIndex, colIndex, row = matrix.length, col = matrix[0].length;
        for (rowIndex = 0; rowIndex < row; rowIndex++) {
            for (colIndex = 0; colIndex < col; colIndex++) {
                if (matrix[rowIndex][colIndex] == 0) {
                    rowSet.add(rowIndex);
                    colSet.add(colIndex);
                }
            }
        }
        for (Integer curRowIndex : rowSet) {
            for (colIndex = 0; colIndex < col; colIndex++) {
                matrix[curRowIndex][colIndex] = 0;
            }
        }
        for (Integer curColIndex : colSet) {
            for (rowIndex = 0; rowIndex < row; rowIndex++) {
                matrix[rowIndex][curColIndex] = 0;
            }
        }
    }
}
