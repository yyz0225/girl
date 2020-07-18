package com.yyz.girl;

import java.util.Scanner;

/**
 * @Author: yyz
 * @Date: 2020/7/7 13:16
 */
public class Test {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入精度：");
        double z=scan.nextDouble();
        System.out.println("在精度为"+z+"的条件下，π约等于：\n"+countPI(z));

        //打印函数在x=7.5处的二阶导
        System.out.println(new DerivedFunction(new DerivedFunction(x ->1.2 * x * x * x + 6* x * x +3.2 * x)).apply(7.5));

    }
    static double countPI(double z){
        double sum=2;
        int n=1;
        int m=3;
        double t=2;
        while(t>z){
            t=t*n/m;
            sum=sum+t;
            n++;
            m+=2;
        }
        return sum;
    }
}