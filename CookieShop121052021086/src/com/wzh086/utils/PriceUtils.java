package com.wzh086.utils;

import java.math.BigDecimal;

public class PriceUtils {
    public static float add(float a, float b){
        BigDecimal biga = new BigDecimal(Float.toString(a));
        BigDecimal bigb = new BigDecimal(Float.toString(b));
        return biga.add(bigb).floatValue();
    }

    public static double add(double a, double b){
        BigDecimal biga = new BigDecimal(Double.toString(a));
        BigDecimal bigb = new BigDecimal(Double.toString(b));
        return biga.add(bigb).floatValue();
    }

    public static double subtract(double a, double b){
        BigDecimal biga = new BigDecimal(Double.toString(a));
        BigDecimal bigb = new BigDecimal(Double.toString(b));
        return biga.subtract(bigb).floatValue();
    }

    public static double subtract(float a, float b){
        BigDecimal biga = new BigDecimal(Float.toString(a));
        BigDecimal bigb = new BigDecimal(Float.toString(b));
        return biga.subtract(bigb).floatValue();
    }
}
