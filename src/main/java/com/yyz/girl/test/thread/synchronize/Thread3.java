package com.yyz.girl.test.thread.synchronize;

/**
 * @Author: yyz
 * @Date: 2019/5/23 15:19
 * join可以指定线程的执行顺序,,,不适用于主线程和子线程之间,主线程一般还是优于子线程执行,
 * 同一级别的线程先join的先执行,join前有线程,那么join前的线程先执行,两个join之间如果混入了没有join的线程,
 * 那么先join的线程执行后,立马也会执行未Join的线程,后join的线程最后执行,且都优于未join的主线程,同一级别指的是单纯的从代码角度来看
 */
public class Thread3 {
    public static void main(String[] args) {

        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread7");
                }
            }
        });
        t7.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread3");
                }
            }
        });
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread5");
                }
            }
        });
        t5.start();

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread6");
                }
            }
        });
        t6.start();

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
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("Thread4");
                }
            }
        });
        t4.start();
        //try {
        //    t4.join();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        System.out.println("main");

    }
}