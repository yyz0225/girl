package com.yyz.girl.test.thread.synchronize;

/**
 * 测试 线程中断状态 升级版
 * @Author: yyz
 * @Date: 2021/7/22 14:58
 * main线程通过调用t.interrupt()从而通知t线程中断，而此时t线程正位于hello.join()的等待中，此方法会立刻结束等待并抛出InterruptedException。
 * 由于我们在t线程中捕获了InterruptedException，因此，就可以准备结束该线程(MyThread1)。在t线程结束前，对hello线程也进行了interrupt()调用通知其中断。
 * 如果去掉这一行代码，可以发现hello线程仍然会继续运行，且JVM不会退出。
 */
public class ThreadInterrupt1 {

    public static void main(String[] args){
        Thread thread = new MyThread1();
        // 启动thread线程
        thread.start();
        // 主线程休眠1秒 休眠期间,thread线程获取到时间片,然后创建helloThread线程并启动,
        // 此时thread线程处于等待helloThread线程的执行完毕的状态中,即一直"卡"在helloThread.join()处(底层是基于Object.wait()方法),
        // helloThread.join()后的代码不会执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断thread线程,thread线程收到中断请求时,就会试图去中止run方法的执行,而此时thread线程自己仍处于等待helloThread执行完毕中,
        // 这个时候就会打断join方法的执行,并抛出一个中断异常,此时只是中断了join方法的执行,join方法后的代码会继续执行;并没有中断HelloThread
        // 线程自己的run方法,故同时还需要通知HelloThread中断
        thread.interrupt();
        // 等待t线程执行结束
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}


class MyThread1 extends Thread {
    @Override
    public void run() {
        Thread helloThread = new HelloThread();
        helloThread.start();
        try {
            helloThread.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        System.out.println("11111111111");
        helloThread.interrupt();
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n+"hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 调用helloThread的interrupt()方法时,发现helloThread线程正处于Thread.sleep()的等待状态,故也会抛出InterruptedException异常,
                // 此时需需要使用break关键字来中断循环
                break;
            }
        }
    }
}