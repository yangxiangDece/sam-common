package com.yang.untils;

import java.math.BigDecimal;

public class MoneyTest {

    private final static BigDecimal INIT_AMOUNT = BigDecimal.valueOf(50000);
    private final static BigDecimal RATE = BigDecimal.valueOf(0.037);
    private final static int YEAR = 3;
    private final static BigDecimal YEAR_ADD = BigDecimal.valueOf(0);

    public static void main(String[] args) {
        BigDecimal result = calc(INIT_AMOUNT, 1, YEAR);
    }

    private static BigDecimal calc(BigDecimal source, int year, int maxYear) {
        if (year >= maxYear + 1) {
            return source;
        }
        BigDecimal a = source.multiply(RATE).setScale(2, BigDecimal.ROUND_DOWN);
        source = source.add(a.add(YEAR_ADD));
        System.out.println("第 " + year + " 年利息：" + a + "，总共：" + source);
        return calc(source, ++year, maxYear);
    }
}
