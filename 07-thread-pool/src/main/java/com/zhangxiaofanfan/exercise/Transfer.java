package com.zhangxiaofanfan.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 习题1: 多线程模拟多人来售票窗口买票的场景
 *
 * @author zhangxiaofanfan
 * @date 2023-12-21 00:11:44
 */
@Slf4j
public class Transfer {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        List<Integer> amountList = new Vector<>();
        TickerWindow window = new TickerWindow(1000);
        // 模拟 2000 人来买票的场景
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                int sell = window.sell(randomAmount());
                try {
                    TimeUnit.MILLISECONDS.sleep(randomAmount());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                amountList.add(sell);
            });
            threadList.add(thread);
            thread.start();
        }
        // 使用 join 等待所有线程执行完成之后再统计票
        for (Thread thread : threadList) {
            thread.join();
        }
        log.info("余票还有: {}", window.getCount());
        log.info("售出票数: {}", amountList.stream().mapToInt(Integer::valueOf).sum());
    }

    /**
     * 使用随机函数模拟买票个数
     *
     * @return  返回随机购买的票数, 区间范围: [1, 5]
     */
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

/**
 * 售票窗口类
 */
class TickerWindow {
    private int count;

    public TickerWindow(int count) {
        this.count = count;
    }

    /**
     * 获取余票数量
     *
     * @return 余票数量
     */
    public int getCount() {
        return count;
    }

    /**
     * 售票方法
     *
     * @param amount    用户想要购买的票数
     * @return          用户真正买到的票数量
     */
    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        }
        return 0;
    }
}
