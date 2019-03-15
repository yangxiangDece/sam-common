package com.yang.arithmetic;

/**
 * 二分查找算法
 */
public class BinSearch {

    private static int num = 0;

    public static void main(String[] args) {
        int[] arrays = {2,4,23,45,56,67,89,111,123,134,156,178,212,222,234,245,267,289};
        System.out.println("数组下标为："+binSearch(arrays,0,arrays.length-1,123));
        System.out.println("查找次数："+num+"次");
//        System.out.println("数组下标为："+binSearch(arrays,123));
//        System.out.println("查找次数："+num+"次");
    }

    //二分查找递归实现
    private static int binSearch(int[] arrays,int start,int end,int target){
        num++;
        int mid = (end - start) >> 1 + start;
        int value = arrays[mid];
        if(value==target){
            return mid;
        }
        if (start >= end) {
            return -1;
        } else if (target>value){
            return binSearch(arrays,mid+1,end,target);
        } else if (target<value){
            return binSearch(arrays,start,mid-1,target);
        }
        return -1;
    }

    //二分查找普通循环实现
    private static int binSearch(int[] arrays,int target){
        int mid = arrays.length >> 1;
        int value = arrays[mid];
        if (value==target){
            return mid;
        }
        int start = 0;
        int end = arrays.length - 1;
        while (start <= end) {
            num++;
            mid = (end - start) >> 1 + start;
            if(target < arrays[mid]){
                end = mid - 1;
            } else if (target > arrays[mid]){
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
