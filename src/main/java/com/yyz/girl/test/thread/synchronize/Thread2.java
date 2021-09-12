package com.yyz.girl.test.thread.synchronize;

/**
 * @Author: yyz
 * @Date: 2019/5/23 15:15
 * 测试主线程和子线程的执行顺序
 * 先进入的线程占用了CPU资源,故一般主线程先于子线程执行
 */
public class Thread2 {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread1");
                }
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            System.out.println("Thread2");
                        }
                    }
                });
                t2.start();
            }
        });
        t1.start();
        System.out.println("main");
    }
}