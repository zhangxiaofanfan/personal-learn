package com.zhangxiaofanfan.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 习题1: 多线程模拟多人来售票窗口买票的场景
 *
 * @author zhangxiaofanfan
 * @date 2023-12-21 00:11:44
 */
@Slf4j
public class ExerciseTicket {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            sellTicket();
        }
    }

    private static void sellTicket() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        List<Integer> amountList = new Vector<>();
        TicketWindow window = new TicketWindow(1000);
        // 模拟 2000 人来买票的场景
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> amountList.add(window.sell(randomAmount())));
            threadList.add(thread);
            thread.start();
        }
        // 使用 join 等待所有线程执行完成之后再统计票
        for (Thread thread : threadList) {
            thread.join();
        }
        log.info("余票还有: {}, 售出票数: {}", window.getCount(), amountList.stream().mapToInt(Integer::valueOf).sum());
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
@Getter
@AllArgsConstructor
class TicketWindow {
    private int count;

    /**
     * 售票方法
     *
     * @param amount    用户想要购买的票数
     * @return          用户真正买到的票数量
     */
    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        }
        return 0;
    }
}
