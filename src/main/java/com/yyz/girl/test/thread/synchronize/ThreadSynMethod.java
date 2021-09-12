package com.yyz.girl.test.thread.synchronize;

/**
 * 线程同步方法
 * synchronized关键字的作用域:实例方法,静态方法和代码块
 * 线程安全的类定义:
 * 一个类被设置成允许多线程正确访问; 例如:StringBuffer,String,Integer,LocalDate等被设计成final的不变类
 * 多线程同时访问只能读,不能写; 例如: Math类只提供静态方法,没有成员变量的类,也是线程安全的
 *
 * @Author: yyz
 * @Date: 2021/7/24 14:46
 */
public class ThreadSynMethod {
    public static void main(String[] args) {
        CounterNew counterNew1 = new CounterNew();
        CounterNew counterNew2 = new CounterNew();
        // 对counterNew1操作的线程
        new Thread(()->{
            counterNew1.addCount(1);
        }).start();
        new Thread(()->{
            counterNew1.decCount(1);
        }).start();

        // 对counterNew2操作的线程
        new Thread(()->{
            counterNew2.addCount(1);
        }).start();
        new Thread(()->{
            counterNew2.decCount(1);
        }).start();
    }
}

/**
 * Counter线程安全类
 */
class CounterNew{
    private int count = 0;
    private static int test = 0;

    /**
     * 读取一个int变量不需要同步
     * 基本类型变量(除long和double)和引用类型变量单行操作是原子操作,不需要同步
     * @return 返回值
     */
    public int getCount() {
        return count;
    }

    // 返回一个包含两个int类型的对象就需要同步了,多线程环境下不能保证原子操作了

    /**
     * 线程安全新增方法
     * @param n 参数n
     */
    public void addCount(int n){
        // 修饰代码块,给当前实例加锁
        synchronized (this){
            count += n;
        }
    }

    /**
     * 线程安全减少方法
     * @param n 参数n
     */
    public void decCount(int n){
        synchronized (this){
            count -= n;
        }
    }

    /**
     * 线程安全新增方法
     * @param n 参数n
     * 修饰实例方法,给当前实例加锁
     */
    public synchronized void addCountNew(int n){
            count += n;
    }

    /**
     * 线程安全减少方法
     * @param n 参数n
     * 修饰实例方法,给当前实例加锁
     */
    public synchronized void decCountNew(int n){
        count -= n;
    }

    /**
     * 线程安全减少方法
     * @param n 参数n
     */
    public static void test(int n){
        // 修饰代码块,锁住当前类的Class实例
        synchronized (Counter.class){
            test += n;
        }
    }

    /**
     * 线程安全减少方法
     * @param n 参数n
     * 修饰静态方法,锁住当前类的Class实例
     */
    public synchronized static void testNew(int n){
        test += n;
    }
}