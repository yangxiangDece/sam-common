package com.yang.common;

public class CreateArrays {

    public static int[] getArrays(int size) {
        int[] arrays = new int[size];
        for (int i = 0; i < arrays.length - 1; i++) {
            arrays[i] = (int) (Math.random() * size * 10);
        }
        return arrays;
    }
}
