package com.yyz.girl.test;

public class AdapterTest {
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();

        /*多线程测试*/
        final Bussiness bussiness = new Bussiness();
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    bussiness.subMethod(i);
                }
            }
        }).start();
        //主线程
        for (int i = 0; i < 3; i++) {
            bussiness.mainMethod(i);
        }
        /*测试结果:
        * 1.线程优先级是1`10,主线程的默认优先级是5
        * 2.一般情况下,主线程总是优于子线程的执行
        * 在一个线程中开启另外一个新线程，则新开线程称为该线程的子线程，子线程初始优先级与父线程相同。不过主线程先启动占用了cpu资源，
        * 因此主线程总是优于子线程。然而，其实设置了优先级，也无法保障线程的执行次序。只不过，优先级高的线程获取CPU资源的概率较大，
        * 优先级低的并非没机会执行。 线程的优先级用1-10之间的整数表示，数值越大优先级越高，默认的优先级为5。
        * 但是如果存在主线程和子线程争抢cpu执行权的话，看运气，谁抢到就让谁执行。因此，此时main主线程和t1、t2子线程之间存在争抢cpu执行权，
        * 因此无法判断谁优先执行.
        * join()可与控制线程的执行顺序问题，对于主线程和子线程的优先执行同样适用。

        * */
    }
}