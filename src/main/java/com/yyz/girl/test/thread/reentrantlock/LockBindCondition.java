package com.yyz.girl.test.thread.reentrantlock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁绑定Condition对象,实现与Wait和Notify,NotifyAll类似的多线程条件等待,不满足条件的等待,满足条件的继续执行
 * Condition.signal(),Condition.signalAll()等价于Object.notify(),Object.notifyAll()
 * @Author: yyz
 * @Date: 2021/7/24 18:42
 */
public class LockBindCondition {
    public static void main(String[] args) throws InterruptedException{
        List<Thread> ts = new ArrayList<>();
        TaskQueue queue = new TaskQueue();
        for (int i = 0; i < 5; i++) {
            Thread getThread = new Thread(()->{
            while (true){
                try {
                    String s = queue.getTask();
                    System.out.println("get task: "+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            });
            getThread.start();
            ts.add(getThread);
        }

        Thread addThread = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                String s = "tt_"+Math.random();
                System.out.println("add task: "+s);
                queue.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        addThread.start();
        addThread.join();
        Thread.sleep(1000);
        // 添加的任务执行完毕之后,关闭内存中仍处于等待状态的线程,以退出程序
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue{
    private Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    // 使用lock.newCondition()方法返回一个当前锁绑定的Condition对象
    private final Condition condition = lock.newCondition();


    public void addTask(String s){
        this.lock.lock();
        try {
            this.queue.add(s);
            // 唤醒所有处于lock锁等待状态的线程
            this.condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.lock.unlock();
        }
    }

    public String getTask() throws InterruptedException{
        this.lock.lock();
        try {
            while (this.queue.isEmpty()){
                // 等价于wait和notify机制中的this.wait()方法方法,用于释放当前实例的锁对象,等待其他线程唤醒,等待唤醒后await()方法返回,并继续执行
                this.condition.await();
                // condition对象的await()方法同Lock锁的tryLock()方法一样,具有超时退出功能
                /*if(condition.await(1, TimeUnit.SECONDS)){
                    // 超时时间内,被其他线程唤醒
                }else{
                    // 指定时间没有被其他线程唤醒
                }*/
            }
            return this.queue.remove();
        } finally {
            this.lock.unlock();
        }
    }
}