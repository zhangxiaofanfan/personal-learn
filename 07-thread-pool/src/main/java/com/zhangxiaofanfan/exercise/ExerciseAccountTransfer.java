package com.zhangxiaofanfan.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 习题2: 多线程模拟账户转账场景
 *
 * @author zhangxiaofanfan
 * @date 2023-12-21 09:59:55
 */
@Slf4j
public class ExerciseAccountTransfer {
    static Random random = new Random();
    public static void main(String[] args) throws InterruptedException {
        Account zhangfan = new Account(1000);
        Account xiaomian = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                zhangfan.transfer(xiaomian, randomAccount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                xiaomian.transfer(zhangfan, randomAccount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("zhangfan 余额是: {}", zhangfan.getAccount());
        log.info("xiaomian 余额是: {}", xiaomian.getAccount());
    }

    /**
     * 使用随机函数, 模拟转账金额[1, 100]
     *
     * @return  模拟需要转账的金额数
     */
    public static int randomAccount() {
        return random.nextInt(100) + 1;
    }
}

/**
 * 账户类, 主要包含转账功能
 */
@Setter
@Getter
@AllArgsConstructor
class Account {
    private int account;

    /**
     * 模拟账户转账过程
     *
     * @param target    需要转入的账户
     * @param amount    待转入金额
     */
    public void transfer(Account target, int amount) {
        synchronized (Account.class) {
            if (this.account >= amount) {
                target.setAccount(target.getAccount() + amount);
                this.account -= amount;
            }
        }
    }
}
