package com.zhangxiaofanfan;

/**
 * 此代码演示两点
 *  1. 对象可以被 GC 时自我拯救;
 *  2. 这种自救机会只有一次, 因为一个对象的 final 方法最多只能被系统调用一次
 *
 * @author zhangxiaofnafan
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    /**
     * 不能等同于C/C++的使用, Java 不建议使用此方法做为回收对象时的析构函数使用, 应当尽量避免此函数的使用;
     *
     * @throws Throwable 异常
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method execute!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }


    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次对自己拯救
        SAVE_HOOK = null;
        System.gc();
        // 因为 Finalizer 方法优先级比较低, 暂停0.5秒, 等待执行
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        // 对象第二次对自己拯救, 代码完全相同, 却失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为 Finalizer 方法优先级比较低, 暂停0.5秒, 等待执行
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }
}
