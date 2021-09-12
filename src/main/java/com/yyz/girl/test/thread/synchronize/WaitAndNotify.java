package com.yyz.girl.test.thread.synchronize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Wait和Notify机制
 * @Author: yyz
 * @Date: 2021/7/24 16:22
 */
public class WaitAndNotify {

    public static void main(String[] args) throws InterruptedException{
        TaskQueue q = new TaskQueue();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(){
                @Override
                public void run(){
                    // 执行Task
                    while (true){
                        try {
                            String s = q.getTask();
                            System.out.println("execute task: "+s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        Thread add = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                // 放入task
                String s = "t-"+Math.random();
                System.out.println("add task: "+s);
                q.addTaskNew(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : ts) {
            // 此处中断getTask的线程原因在于,新添加的10个任务均已经执行完了,但内存中任有5个线程处于wait状态,故需要手动中断所有线程
            t.interrupt();
        }
    }
}


class TaskQueue {
    private Queue<String> queue = new LinkedList<>();

    /**
     * synchronized关键字解决了多线程竞争问题
     *
     * @param s 任务
     */
    public synchronized void addTask(String s) {
        this.queue.add(s);
        // 唤醒在this锁等待的线程
        this.notify();
    }

    public synchronized void addTaskNew(String s) {
        this.queue.add(s);
        // 唤醒在this锁等待的所有线程
        this.notifyAll();
    }

    // 多线程协调问题 线程间如何进行通信
    // 多线程协调运行的原则: 当条件不满足时,线程进入等待状态;当条件满足时,线程被唤醒,继续执行任务
    public synchronized String getTask() throws InterruptedException{
        // 此处使用while而不是if,多个线程被唤醒后,只有一个线程能获取this锁,此刻,wait()方法已经执行完并返回了,该线程执行queue.remove()可以获取到队列的元素,
        // 然而剩下的线程如果获取this锁后,执行queue.remove(),此时队列可能已经没有元素了,所以,要始终在while循环中wait(),并且每次唤醒后拿到this锁就必须要再次判断
        while (queue.isEmpty()) {
            // 调用wait方法前,当前线程进入getTask方法时,就已经获取了当前实例的锁
            // 释放this锁,当前线程进入等待状态,wait方法不会返回,直到当前线程被其他线程唤醒后,wait方法才会返回,然后继续执行下一条语句
            this.wait();
        }
        return queue.remove();
    }
}
