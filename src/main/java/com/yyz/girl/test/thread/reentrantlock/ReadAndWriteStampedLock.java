package com.yyz.girl.test.thread.reentrantlock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock锁优化了ReadWriteLock读写锁中当有线程读的时候,线程写是进行不了的,需要等读操作释放读锁之后,才能获取写锁,这是一种悲观读锁
 * StampedLock锁允许读的过程中获取写锁后写入,需要额外的代码来判断是否有写入,小概率的写入导致的数据不一致,检测出来后重新读取一遍即可,
 * 这是一种乐观读锁
 * @Author: yyz
 * @Date: 2021/7/24 20:45
 */
public class ReadAndWriteStampedLock {
}

class Point{
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    /**
     * 写入操作
     * @param deltaX x
     * @param deltaY y
     */
    public void move(double deltaX, double deltaY){
        // 获取写锁
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        }finally {
            // 释放写锁
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * 读取操作
     * @return 返回值
     */
    public double distanceFromOrigin(){
        // 获得一个乐观锁读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 注意下面两房代码不是原子操作
        // 假设x,y=100,200
        double currentX = x;
        // 此处已经读到x=100,但x,y可能会写线程修改为300,400
        double currentY = y;
        // 此处已经读取到y,如果没有写入,读取是正确的100,200
        // 如果有写入,读取是错误的300,400
        if (!stampedLock.validate(stamp)) {
            // 获取一个悲观锁读
            stamp = stampedLock.readLock();
            try{
                currentX = x;
                currentY = y;
            }finally {
                // 释放悲观锁读
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX+currentY*currentY);
    }
}

