package com.zhangxiaofanfan.hotcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 10:59:16
 */
public class HotCode014 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 15},
                {15, 18}
        };
        HotCode014 hotCode014 = new HotCode014();
        Arrays.stream(hotCode014.merge(intervals)).forEach(nums -> System.out.println(Arrays.toString(nums)));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(nums -> nums[0]));
        List<int[]> list = new ArrayList<>();
        for (int left = 0, right = left; left < intervals.length; left = right + 1, right = left) {
            while (right < intervals.length) {
                if (right == intervals.length - 1 || intervals[right][1] < intervals[right + 1][0]) {
                    list.add(new int[] {intervals[left][0], intervals[right][1]});
                    break;
                } else {
                    intervals[right + 1][1] = Math.max(intervals[right][1], intervals[right + 1][1]);
                }
                right++;
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
