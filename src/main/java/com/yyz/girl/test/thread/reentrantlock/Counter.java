package com.yyz.girl.test.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁 拥有更精确的语义和更好的性能
 * 可重入锁,默认非公平锁,可设置为公平锁
 * @Author: yyz
 * @Date: 2021/7/24 17:57
 */
public class Counter {
    private int count = 0;
    private final Lock reentrantLock = new ReentrantLock();


    /**
     * 使用synchronized关键字实现同步,不需要考虑异常
     * @param n 参数n
     */
    public synchronized void add(int n){
        count += n;
    }

    /**
     * ReentrantLock锁实现同步,是java代码实现的锁,需要考虑异常,故解锁应放在finally块中,以确保锁的正常释放
     * @param n 参数n
     */
    public void addNew (int n){
        reentrantLock.lock();
        try {
            count += n;
        }finally {
            reentrantLock.unlock();
        }
    }

    /**
     * ReentrantLock锁实现同步,是java代码实现的锁,需要考虑异常,故解锁应放在finally块中,以确保锁的正常释放;
     * ReentrantLock锁还可以尝试获取锁,在未获取锁的情况下,可以做一些额外的处理,而不是无限制的等待下去
     * @param n 参数n
     */
    public void addNew2 (int n) throws InterruptedException{
        if(reentrantLock.tryLock(1, TimeUnit.SECONDS)){
            try {
                count += n;
            }finally {
                reentrantLock.unlock();
            }
        }

    }
}