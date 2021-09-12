package com.yyz.girl.test.thread.synchronize;

/**
 * 可重入锁和死锁
 * 可重入锁: JVM允许同一个线程重复获得锁,这种类型的锁就称为可重入锁(同一个实例,线程同步方法中,可以进入另一个线程同步方法)
 * JAVA线程锁死可重入锁,所以,获取锁的时候,不但要判断是否第一次获取锁,还要记录这是第几次获取锁;每获取一次锁,记录+1,每退出synchronized块
 * 记录-1,等到记录为0的时候,才会真正释放锁;
 * 死锁: 两个线程各自持有不同的锁,然后各自试图获取对方的锁,造成了无限等待下去,就是死锁;若发生死锁,没有任何机制可以解除死锁,只能强制结束JVM进程
 * 防止死锁: 会造成死锁的线程,要保持同步,获取锁的顺序要一致;超时中断,释放锁;
 *
 * @Author: yyz
 * @Date: 2021/7/24 15:19
 */
public class ThreadSynRetry {
}

/**
 * 可重入锁
 */
class RetryLock {
    private int count = 0;

    public synchronized void add(int n) {
        if (n < 0) {
            dec(-n);
        } else {
            count += n;
        }
    }

    public synchronized void dec(int n) {
        count += n;
    }
}

/**
 * 死锁
 */
class DeadLock{

    private  int value = 0;
    private  int another = 0;
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public void add(int n){
        synchronized (lockA){
            this.value += n;
            synchronized (lockB){
                this.another += n;
            }
        }
    }

    /**
     * 死锁代码块
     * @param n 参数n
     */
    /*public void dec(int n){
        synchronized (lockB){
            this.value -= n;
            synchronized (lockA){
                this.another -= n;
            }
        }
    }*/

    /**
     * 可能发生死锁的位置,多个线程获取锁的顺序要一致
     * @param n 参数n
     */
    public void dec(int n){
        synchronized (lockA){
            this.value -= n;
            synchronized (lockB){
                this.another -= n;
            }
        }
    }
}