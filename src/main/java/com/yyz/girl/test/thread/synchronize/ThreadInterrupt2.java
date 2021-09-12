package com.yyz.girl.test.thread.synchronize;

/**
 * 设置线程中断标志
 * @Author: yyz
 * @Date: 2021/7/22 16:17
 * 我们通常会用一个running标志位来标识线程是否应该继续运行，在外部线程中，通过把HelloThread.running置为false，就可以让线程结束
 */
public class ThreadInterrupt2 {

    public static void main(String[] args) throws InterruptedException{
        HelloThread1 thread = new HelloThread1();
        thread.start();
        Thread.sleep(1);
        thread.running = false;
    }
}

class HelloThread1 extends Thread{
    public volatile boolean running = true;
    @Override
    public void run(){
        int n = 0;
        while (running){
            n++;
            System.out.println(n+" hello!");
        }
        System.out.println("end");
    }
}

