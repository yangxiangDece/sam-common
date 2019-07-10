package com.yang.datastructures.sort;


import java.util.Arrays;

/**
 * 计数排序
 * 1、找出待排序的数组中最大和最小的元素；
 * 2、统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 3、对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 4、反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arrays = {88, 4, 34, 23, -1, 3, -12, 0};
        System.out.print("before sort：");
        System.out.println(Arrays.toString(arrays));
        sort(arrays);
        System.out.println();
        System.out.print(" after sort：");
        System.out.println(Arrays.toString(arrays));
    }

    private static void sort(int[] array) {
        int min = array[0], max = array[0];
        //找出最大值，最小值，确定数组范围
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
        int[] temp = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            // array[i] - min 这里的min就是偏移量，因为当数过大的时候 通过max - min后 数组中是没有这么大的下标的，所以需要减去偏移量
            temp[array[i] - min]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (temp[i] > 0) {
                // 这里还原数据时，需要加上之前的偏移量min 得到本来的数据
                array[index] = i + min;
                temp[i]--;
                index++;
            } else {
                i++;
            }
        }
    }

    private static void countSort(int[] array) {
        //1.得到数列的最大值和最小值
        int max = array[0], min = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
        //2.创建统计数组并统计对应元素个数，数组大小为最大值最小值的差值 + 1
        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            // array[i] - min 这里的min就是偏移量，因为当数过大的时候 通过max - min后 数组中是没有这么大的下标的，所以需要减去偏移量
            countArray[array[i] - min]++;
        }
        //3.统计数组做变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //4.遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
        }
        //将排好序的数组复制覆盖原数组
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }
}
