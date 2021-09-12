package com.yyz.girl.test.thread.synchronize;

/**
 * 线程同步
 * 使用synchronized关键字实现线程同步,保证同一时刻只有一个线程操作共享资源;
 * 但synchronized代码块无法并行,且加锁和释放锁消耗一定的时间,会带来性能的下降;
 * synchronized关键字在获得锁和释放锁的时候,会强制刷新共享资源(如果有修改),将变化同步到主内存中;
 * 不必未每个线程操作的共享资源进行加锁,组与组之间不存在资源竞争,可以对共享资源进行分组,对同一组的资源,会出现竞争的资源加锁;
 * 单行基本类型(long和double除外)赋值和引用类型赋值,是原子操作不需要进行同步(x64平台,long和double赋值是原子操作实现);
 * 多行需要进行同步,但可使用将多个外部赋值转换为单行赋值(比如转为数组)的方法,来变成单行的原子操作;
 * @Author: yyz
 * @Date: 2021/7/24 10:34
 */
public class ThreadSyn {
    public static void main(String[] args) throws InterruptedException{
        Thread addThread = new AddThread();
        Thread decThread = new DecThread();
        addThread.start();
        decThread.start();
        addThread.join();
        decThread.join();
        System.out.println(Counter.count);
    }
}

/**
 * 存放共享变量的类
 */
class Counter{
    /**
     * 对象锁,synchronized关键字实现同步效果,要使用同一把锁
     */
    public static final Object lock = new Object();
    public static int count = 0;

    /**
     * 每个线程加synchronized锁,性能最低,在不存在资源竞争的共享资源中,可使用分组锁
     * synchronized分组,组之间不存在资源竞争,可以使用同一把锁
     */
    public static final Object lockStudent = new Object();
    public static final Object lockTeacher = new Object();
    public static int countStudent = 0;
    public static int countTeacher = 0;
}
class AddThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lock){
                Counter.count +=1;
            }
        }
    }
}
class DecThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lock) {
                Counter.count -=1;
            }
        }
    }
}

class AddStudentThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lockStudent){
                Counter.countStudent +=1;
            }
        }
    }
}
class DecStudentThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lockStudent) {
                Counter.countStudent -=1;
            }
        }
    }
}

class AddTeacherThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lockTeacher){
                Counter.countTeacher +=1;
            }
        }
    }
}

class DecTeacherThread extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            synchronized (Counter.lockTeacher) {
                Counter.countTeacher -=1;
            }
        }
    }
}