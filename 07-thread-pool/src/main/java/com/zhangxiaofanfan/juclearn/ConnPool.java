package com.zhangxiaofanfan.juclearn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 使用享元模式创建连接池, 不可变操作
 *
 * @author zhangxiaofanfan
 * @date 2024-01-25 09:16:21
 */
public class ConnPool {
    public static void main(String[] args) {
        Pool pool = new Pool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Conn borrowConn = pool.borrow();
                borrowConn.operation();
                pool.free(borrowConn);
            }).start();
        }
    }
}

/**
 * 连接池对象
 */
@Slf4j
class Pool {
    // 1. 设置连接池大小, V1.0大小不可变
    private final int poolSize;
    // 2. 设置存放连接对象的数组池
    private final Conn[] connPool;
    // 3. 使用原子数组类实现连接池里连接对象的空闲状态
    private final AtomicIntegerArray state;

    public Pool(int poolSize) {
        this.poolSize = poolSize;
        this.connPool = new Conn[poolSize];
        this.state = new AtomicIntegerArray(new int[poolSize]);
        for (int i = 0; i < poolSize; i++) {
            connPool[i] = new Conn("连接" + (i + 1));
        }
    }

    /**
     * 向连接池中借用连接对象
     *
     * @return  返回空闲的链接对象
     */
    public Conn borrow() {
        while (true) {
            for (int i = 0; i < this.poolSize; i++) {
                if (state.compareAndSet(i, 0, 1)) {
                    log.debug("borrow: {}", this.connPool[i]);
                    return this.connPool[i];
                }
            }
            synchronized (this) {
                try {
                    log.debug("waiting......");
                    wait();
                } catch (InterruptedException e) {
                    log.error("执行等待被打断, 抛出异常: {}", e.getMessage());
                }
            }
        }
    }

    public void free(Conn conn) {
        for (int i = 0; i < this.poolSize; i++) {
            if (connPool[i] == conn) {
                state.compareAndSet(i, 1, 0);
                log.debug("free: {}", conn);
                synchronized (this) {
                    notifyAll();
                }
                return;
            }
        }
        log.warn("归还连接池对象异常, 该连接不属于此连接池对象, 请确认后重新进行归还操作");
    }
}

/**
 * 连接对象, 建议只添加名称操作
 */
@Slf4j
@Data
@ToString
@AllArgsConstructor
class Conn {
    private String name;

    public void operation() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            log.error("执行休眠被打断, 抛出异常: {}", e.getMessage());
        }
    }
}
