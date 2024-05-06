package com.zhangxiaofanfan.juclearn.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过账户问题引出 CAS 操作
 *
 * @author zhangxiaofanfan
 * @date 2024-01-24 09:08:22
 */
@Slf4j
public class AccountQuestion {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("线程不安全的实现: ");
        Account account = new AccountUnsafe(10000);
        Account.demo(account);

        System.out.print("线程安[加锁]的实现: ");
        Account account1 = new AccountSafe(10000);
        Account.demo(account1);

        System.out.print("线程安全[CAS]的实现: ");
        Account account2 = new AccountCas(10000);
        Account.demo(account2);
    }
}

class AccountUnsafe implements Account{
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void withdraw(Integer account) {
        balance -= account;
    }
}

/**
 * 使用 cas 实现操作同一账户对象的安全操作
 */
class AccountCas implements Account {

    private final AtomicInteger balance;

    public AccountCas(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public int getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer account) {
        while (true) {
            // 获取待修改的金额
            int prev = balance.get();
            // 计算出修改之后的金额
            int next = prev - account;
            // 通过 cas 操作完成数据修改
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

/**
 * 使用锁机制实现账户的线程安全操作
 */
class AccountSafe implements Account {
    private Integer balance;

    public AccountSafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

    @Override
    public void withdraw(Integer account) {
        synchronized (this) {
            balance -= account;
        }
    }
}

interface Account {
    /**
     * 获取余额方法
     *
     * @return  返回余额
     */
    int getBalance();

    /**
     * 取款方法
     *
     * @param account   需要取款金额
     */
    void withdraw(Integer account);

    static void demo(Account account) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> account.withdraw(10)));
        }
        long start = System.nanoTime();

        list.forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }
        long end = System.nanoTime();
        System.out.println(account.getBalance() + ", cost: " + (end - start) / 1000_000 + "ms");
    }
}
