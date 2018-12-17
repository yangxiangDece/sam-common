package com.yang.common;

/**
*
* @description 快速排序
* @author YangXiang
* @time 2018/12/15 11:50
*
*/
public class QuickSort {

    public static int getMiddle(int[] arrays,int left,int right){
        int temp=arrays[left];//将数组中的第一个作为中轴
        while (left < right) {
            //从右边向左边走
            while (left < right && arrays[right] >= temp) {
                right--;
            }
            arrays[left]=arrays[right];//将右边小于中轴的记录移到左边去
            //从左边向右边走
            while (left < right && arrays[left] >= temp){
                left++;
            }
            arrays[right]=arrays[left];//将左边大于中轴的记录移到右边去
        }
        arrays[left]=temp;//两边向中轴走碰见
        return left;//返回中轴位置
    }

    public static void quickSort(int[] arrays,int left,int right){
        if(left < right){
            int middle = getMiddle(arrays,left,right);//得到中轴位置
            quickSort(arrays,left,middle-1);//从左边往右边走
            quickSort(arrays,middle+1,right);//从右边往左边走
        }
    }

    public static void main(String[] args) {
        int[] arrays={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        quickSort(arrays,0,arrays.length-1);
        for (int i=0;i<arrays.length-1;i++) {
            System.out.print(arrays[i]+"\t");
        }
    }
}
