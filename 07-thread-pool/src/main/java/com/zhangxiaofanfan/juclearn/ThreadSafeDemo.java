package com.zhangxiaofanfan.juclearn;

import java.util.ArrayList;

/**
 * 线程安全问题demo: 主要演示的是成员、局部变量在多线程下的安全问题
 *
 * @author zhangxiaofanfan
 * @date 2023-12-20 09:21:41
 */
public class ThreadSafeDemo {
    private static final Integer THREAD_NUMBER = 2;
    private static final Integer LOOP_NUMBER = 200;
    public static void main(String[] args) {
//        testThreadUnsafe();
//        testThreadSafe();
        testThreadSubSafeEscape();
    }

    public static void testThreadUnsafe() {
        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> test.method(LOOP_NUMBER), "thread-" + (i + 1)).start();
        }
    }

    public static void testThreadSafe() {
        ThreadSafe test = new ThreadSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> test.method(LOOP_NUMBER), "thread-" + (i + 1)).start();
        }
    }

    public static void testThreadSubSafeEscape() {
        ThreadSubSafeEscape test = new ThreadSubSafeEscape();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> test.method(LOOP_NUMBER), "thread-" + (i + 1)).start();
        }
    }
}

class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();

    public void method(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            methodA();
            methodB();
        }
    }

    private void methodA() {
        list.add("a");
    }

    private void methodB() {
        list.remove(0);
    }
}

class ThreadSafeEscape {

    public void method(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            methodA(list);
            methodB(list);
        }
    }

    public void methodA(ArrayList<String> list) {
        list.add("a");
    }

    public void methodB(ArrayList<String> list) {
        list.remove(0);
    }
}

/**
 * 改进后线程安全的访问方式
 */
class ThreadSafe {

    public void method(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            methodA(list);
            methodB(list);
        }
    }

    private void methodA(ArrayList<String> list) {
        list.add("a");
    }

    public void methodB(ArrayList<String> list) {
        list.remove(0);
    }
}

/**
 * 通过重写父类 public 方法，将使用的变量进行逃逸到其他线程使用
 */
class ThreadSubSafeEscape extends ThreadSafeEscape {
    @Override
    public void methodB(ArrayList<String> list) {
        new Thread(() -> list.remove(0), "thread").start();
    }
}
