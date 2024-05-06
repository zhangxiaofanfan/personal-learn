package com.zhangxiaofanfan.juclearn.designpatterns;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 保护性暂停升级: 多线程等待线程结果(每个线程知会对应一个GO对象)
 *  场景: 快递员向收信箱里投快递, 居民从信箱里收快递;
 *
 * @author zhangxiaofanfan
 * @date 2024-01-02 10:02:33
 */
public class MailBoxTest {
    private static final Integer SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        MailBox<String> mailBox = new MailBox<>(SIZE);
        // 模拟 {SIZE} 位居民超时等待5秒去接收邮件;
        for (int i = 1; i <= SIZE; i++) {
            Resident<String> resident = new Resident<>(i, mailBox);
            resident.setName("居民" + i);
            resident.start();
        }
        // 模拟 {SIZE} 位快递员没过一秒向对应邮箱中投递一封邮件
        for (int i = 1; i <= SIZE; i++) {
            Postman<String> postman = new Postman<>(i, mailBox, "邮件内容" + i);
            postman.setName("快递员"+ i);
            postman.start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

/**
 * 居民类, 进行收取信件动作
 */
@Slf4j
class Resident<T> extends Thread{
    private final Integer boxId;
    private final MailBox<T> mailBox;

    public Resident(Integer boxId, MailBox<T> mailBox) {
        this.boxId = boxId;
        this.mailBox = mailBox;
    }

    /**
     * 去指定邮件箱里去接收信件, 超时等待时间为 5 秒
     */
    @Override
    public void run() {
        T mail = mailBox.getResidentMail(boxId, 3000);
        log.info("用户编号为: {}, 邮件内容为: {}", boxId, mail);
    }
}

/**
 * 快递员类, 进行投递信件动作
 */
class Postman<T> extends Thread {

    private final T mail;
    private final Integer boxId;
    private final MailBox<T> mailBox;

    public Postman(Integer boxId, MailBox<T> mailBox, T mail) {
        this.mail = mail;
        this.boxId = boxId;
        this.mailBox = mailBox;
    }

    /**
     * 去指定邮件箱里去投递信件
     */
    @Override
    public void run() {
        this.mailBox.sendResidentMail(boxId, mail);
    }
}

/**
 * 邮箱类, 里面有多个邮箱盒, 不同的邮箱盒对应着不同住户
 */
@Slf4j
class MailBox<T> {
    private final int boxSize;
    private final Map<Integer, Box<T>> mailBoxes = new ConcurrentHashMap<>();

    public MailBox(Integer boxSize) {
        this.boxSize = boxSize;
        for (int i = 1; i <= boxSize; i++) {
            mailBoxes.put(i, new Box<>());
        }
    }

    /**
     * 带有超时的、线程安全的获取居民邮件方法
     *
     * @param boxId     住户邮箱号码
     * @param timeout   等待超时时间
     * @return          邮件内容
     */
    public T getResidentMail(Integer boxId, long timeout) {
        if (boxId <= 0 || boxId > boxSize) {
            log.info("不存在该邮箱柜, 退出执行");
            return null;
        }
        T mail = this.mailBoxes.get(boxId).receive(timeout);
        log.info("获取邮件操作, 邮件箱号码为: {}, 邮件内容为: {}", boxId, mail);
        return mail;
    }

    /**
     * 线程安全的向邮箱中投递信件的方法
     *
     * @param boxId 邮箱id
     * @param mail  邮件内容
     */
    public void sendResidentMail(Integer boxId, T mail) {
        if (boxId <= 0 || boxId > boxSize) {
            log.info("不存在该邮箱柜, 退出执行");
            return ;
        }
        log.info("投递邮件操作, 邮件箱号码为: {}, 信件内容为: {}", boxId, mail);
        this.mailBoxes.get(boxId).sendMail(mail);
    }
}

/**
 * 单个邮箱盒类, 用户和快递员投递邮件使用的封装类
 *
 * @param <T> 邮件内容泛型类
 */
@Slf4j
class Box<T> {
    private T mail;

    /**
     * 带有失效、线程安全的接收邮件方法
     *
     * @param timeout   超时失效, 单位毫秒ms
     * @return  邮件数据
     */
    public T receive(long timeout) {
        long beginTime = System.currentTimeMillis();
        long remainTime;
        synchronized (this) {
            while (mail == null) {
                remainTime = timeout - (System.currentTimeMillis() - beginTime);
                if (remainTime <= 0) {
                    break;
                }
                try {
                    this.wait(remainTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("等待接收邮件时间为: {}, 邮件内容为: {}", System.currentTimeMillis() - beginTime, mail);
            return mail;
        }
    }

    /**
     * 向邮箱中投递邮件
     *
     * @param mail  需要投递的邮件
     */
    public synchronized void sendMail(T mail) {
        this.mail = mail;
        this.notifyAll();
    }
}
