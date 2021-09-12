package com.yyz.girl.test.thread.synchronize;

/**
 * 测试线程中断
 *
 * @Author: yyz
 * @Date: 2021/7/22 14:16
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException{
        Thread t = new MyThread();
        // 启动线程
        t.start();
        // 主线程暂停1毫秒
        Thread.sleep(1);
        // 中断t线程
        t.interrupt();
        // 主线程等待t线程中断结束后,才输出end
        t.join();
        System.out.println("end");

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            //System.out.println(n+"中断状态:"+isInterrupted());
            n++;
            System.out.println(n + "hello!");
        }
    }
}