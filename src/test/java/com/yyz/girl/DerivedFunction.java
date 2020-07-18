package com.yyz.girl;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @Author: yyz
 * @Date: 2020/7/7 13:36
 */
public class DerivedFunction implements Function<Double, Double> {
    private static final double DELTA_X = 0.000001;
    private Function<Double, Double> function;

    public DerivedFunction(Function<Double, Double> function) {
        this.function = function;
    }

    @Override
    public Double apply(Double x) {
        return (function.apply(x + DELTA_X) - function.apply(x)) / DELTA_X;
    }

    public static void main(String[] args) {
        //求x=7.5的导数
        double  out = new DerivedFunction(x ->1.2 * x * x * x + 6* x * x +3.2 * x).apply(7.5);
        System.out.println(out);
        //保留两位小数
        System.out.println(new DecimalFormat("0.00").format(out));
    }
}