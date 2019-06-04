package com.yyz.girl.test;

class Bussiness {

    private boolean subFlag = true;

    public synchronized void mainMethod(int j) {
        while (subFlag) {
            try {
                System.out.println("mainMethod is waitting --"+j);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " : main thread running loop count -- " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subFlag = true;
        notify();
    }

    public synchronized void subMethod(int j) {
        while (!subFlag) {
            try {
                System.out.println("subMethod is waitting --"+j);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.err.println(Thread.currentThread().getName()
                    + " : sub thread running loop count -- " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subFlag = false;

        notify();
    }
}