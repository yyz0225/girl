package com.yyz.girl.test.thread.reentrantlock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock锁保证同一时刻只有一个线程操作临界区代码,效率低下
 * ReadWriteLock读写锁,读锁和写锁分开,允许多个线程同时读,但只要有一个线程写,其他线程就必须等待(提高性能)
 * @Author: yyz
 * @Date: 2021/7/24 19:43
 */
public class ReadWriteLockMain {

    public static void main(String[] args) throws InterruptedException{
        ReadAndWriteLock readAndWriteLock = new ReadAndWriteLock();
        for (int i = 0; i < 5; i++) {
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWriteLock.inc(0);
                }
            });
            writeThread.start();
        }
        Thread.sleep(10);
        System.out.println(Arrays.toString(readAndWriteLock.get()));
    }

}

class ReadAndWriteLock{
    private int[] counts = new int[10];
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * 扩容方法
     */
    public void inc(int index){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            counts[index] += 1;
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 读取方法
     * @return 返回值
     */
    public int[] get(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            return Arrays.copyOf(counts,counts.length);
        }finally {
            readLock.unlock();
        }
    }
    
}