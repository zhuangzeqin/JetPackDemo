package com.eeepay.zzq.jetpackdemo.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by zw on 2016/4/14 0014.
 */
public class MathUtils {

    // 默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    private static final int digit = 2;// 保留小数的位数

    public static String twoNumber(int number) {
        BigDecimal bigDecimal = new BigDecimal(Integer.toString(number));
        return bigDecimal.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String twoNumber(float number) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(number));
        return bigDecimal.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String twoNumber(double number) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        return bigDecimal.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String twoNumber(String number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * String类型转换成int类型
     * @param str
     * @return
     */
    public static int String2Int(String str){
        if(TextUtils.isEmpty(str)){
            return 0;
        }
        int result;
        if(str.contains(".")){
            result = (int) Float.parseFloat(str);
        }else{
            result = Integer.parseInt(str);
        }
        return result;
    }

    /**
     * String类型转化称float类型
     * @param str
     * @return
     */
    public static float String2Float(String str){
        if(TextUtils.isEmpty(str)){
            return 0.00f;
        }
        return Float.parseFloat(str);
    }


}
