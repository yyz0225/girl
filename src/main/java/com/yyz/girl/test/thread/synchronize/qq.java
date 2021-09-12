package com.yyz.girl.test.thread.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yyz
 * @Date: 2021/7/4 11:36
 * 同一个类中两个方法都加了同步锁,多线程中能否同时访问这个类中的两个方法
 * 区分:synchronized和Lock锁
 * synchronized关键字如果是对象锁,那么多线程可以同时访问这个类中的两个方法;如果是类锁,锁定的时当前类对的Class实例,只有一把锁,不能同时访问
 * lock锁,让等待锁的线程中,需要释放锁之后才能访问,故多线程不能同时访问这个类中的两个方法.
 */
public class qq {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    /**
     * 方法1
     */
    public Runnable run1 = new Runnable() {
        public void run() {
            //synchronized(qq.class) { //设置关键字 synchronized，以当前类Class实例为锁
            synchronized (this) { //设置关键字 synchronized，以当前类对象为锁
                while (count < 1000) {
                    try {
                        //打印是否执行该方法
                        System.out.println(Thread.currentThread().getName() + " run1: " + count++);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 方法2
     */
    public Runnable run2 = new Runnable() {
        public void run() {
            //synchronized(qq.class) { //设置关键字 synchronized，以当前类Class实例为锁
            synchronized (this) { //设置关键字 synchronized，以当前类对象为锁
                while (count < 1000) {
                    try {
                        System.out.println(Thread.currentThread().getName()
                                + " run2: " + count++);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 方法3
     */
    public Runnable run3 = new Runnable() {
        public void run() {
            lock.lock();
            while (count < 1000) {
                try {
                    //打印是否执行该方法
                    System.out.println(Thread.currentThread().getName() + " run3: " + count++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    };

    /**
     * 方法4
     */
    public Runnable run4 = new Runnable() {
        public void run() {
            lock.lock();
            while (count < 1000) {
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " run4: " + count++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        qq t = new qq(); //创建一个对象
        //new Thread(t.run1).start(); //获取该对象的方法 1
        //new Thread(t.run2).start(); //获取该对象的方法 2
        new Thread(t.run3).start(); //获取该对象的方法 3
        new Thread(t.run4).start(); //获取该对象的方法 4
    }
}
