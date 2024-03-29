package com.yang.datastructures.sort;

import com.yang.common.CreateArrays;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 插入排序的基本方法是：每步将一个待排序的元素，按其排序码大小插入到前面已经排好序的一组元素的适当位置上去，直到元素全部插入为止。
 * <p>
 * 可以选择不同的方法在已经排好序的有序数据表中寻找插入位置，依据查找方法的不同，有多种插入排序方法。下面是常用的三种：
 * 直接插入排序、折半插入排序、希尔排序
 * <p>
 * 直接插入排序是一种简单的插入排序法，其基本思想是：把待排序的记录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，直到所有的记录插入完为止，得到一个新的有序序列。
 * 直接插入排序基本思想：当插入第i（i>1）个元素时，前面的data[0],data[1]……data[i-1]已经排好序。这时用data[i]的排序码与data[i-1],data[i-2],……
 * 的排序码顺序进行比较，找到插入位置即将data[i]插入，原来位置上的元素向后顺序移动。
 * <p>
 * 折半插入排序基本思想：设元素序列data[0],data[1],……data[n-1]。其中data[0],data[1],……data[i-1]是已经排好序的元素。在插入data[i]时，
 * 利用折半搜索法寻找data[i]的插入位置。
 * <p>
 * 希尔排序（shell sort）这个排序方法又称为缩小增量排序，是1959年D·L·Shell提出来的。该方法的基本思想是：设待排序元素序列有n个元素，
 * 首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，所有距离为increment的元素放在同一个子序列中，
 * 在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，重复上述子序列划分和排序工作。直到最后取increment=1，将所有元素放在同一个子序列中排序为止。
 * <p>
 */
public class StraightInsertSort {

    public static void main(String[] args) {
        //加入之前的1，3，5，8已经按照直接插入排序排序好了，现在我们需要对2进行排序。 首先将2存在一个临时变量temp中，
        //然后指针从2的前一位开始判断，如果前一位比2大，则将前一位往后移，以此类推，直到找到比2小的元素。这时将2插入进去。
        int[] array = CreateArrays.getArrays(15);
        System.out.print("before sort：");
        System.out.println(Arrays.toString(array));
//        directInsertSort(array);
        shellSort(array);
//        shellSort2(array);
        System.out.println();
        System.out.print("after sort：");
        System.out.println(Arrays.toString(array));
    }

    // 直接插入排序
    // 慢慢的数据前面将变得有序，比如第一个和第二个有序了，第三个再往前比较，然后第四个，依次类推，相当于是后面的元素在插入一个有序的集合中，所以叫做插入排序
    private static void directInsertSort(int[] array) {
        int temp, j;
        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            if (i != j) {
                array[j] = temp;
            }
        }
    }

    // 希尔排序 - 移位法
    private static void shellSort(int[] arrays) {
        int temp, j;
        // 增量每次都/2
        for (int step = arrays.length / 2; step > 0; step /= 2) {
            // 从增量那组开始进行插入排序，直至完毕
            for (int i = step; i < arrays.length; i++) {
                temp = arrays[i];
                j = i;
                // j - step 就是代表与它同组隔壁的元素
                while (j - step >= 0 && arrays[j - step] > temp) {
                    arrays[j] = arrays[j - step];
                    j = j - step;
                }
                if (i != j) {
                    arrays[j] = temp;
                }
            }
        }
    }

    // 希尔排序 - 交换法 效率较低
    private static void shellSort2(int[] arrays) {
        int temp;
        // 外层循环 控制一直分组 直到gap等于0
        for (int gap = arrays.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrays.length; i++) {
                // 遍历各组中的有元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，就交换
                    if (arrays[j] > arrays[j + gap]) {
                        temp = arrays[j];
                        arrays[j] = arrays[j + gap];
                        arrays[j + gap] = temp;
                    }
                }
            }
        }
    }
}
