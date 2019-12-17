package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    基数排序(radixSort): 属于分配式排序(Distribution sort)，又称"桶子法"(bucket sort\bin sort)
    > 1. 找到基数个数,确定分配次数
    > 2. 建立0-9的基数桶，将数组元素按其所得基数放入对应序号的桶中
    > 3. 将桶中数据还原至数组

    优点：稳定性的排序
    缺点：耗费大量的数据空间
 */
public class A7_RadixSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{80,457,5,7,1,3,6,2};
        arr1 = new int[]{5,4,3,2,1,0};
        radixSort(arr1);
        System.out.println("arr1: " + Arrays.toString(arr1));

//        System.exit(0);
        int[] arr2 = new int[8000000];   // 800万 ~= 2000ms
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        radixSort(arr2);
        Instant time2 = Instant.now();
        System.out.println("time:" + (time2.toEpochMilli() - time1.toEpochMilli()));
//        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }

    public static void radixSort(int[] arr) {
        // 初始化桶、桶标记空间
        int[][] list = new int[10][arr.length];
        int[] bucketIndex = new int[10];
        // 准备循环次数、基数
        int max = getMax(arr);
        int radix;

        for (int i = 1; max / (int)Math.pow(10,i-1) > 0; i++) {
            // 按基数 将数字放入桶中
            for (int value : arr) {
                radix = value / (int) Math.pow(10, i - 1) % 10;
                list[radix][bucketIndex[radix]++] = value;
            }
            // 将桶中数字 还原
            int index = 0;
            for (int k = 0; k < bucketIndex.length; k++) {
                if (bucketIndex[k] != 0){
                    for (int l = 0; l < bucketIndex[k]; l++) {
                        arr[index++] = list[k][l];
                    }
                bucketIndex[k] = 0;
                }
            }
        }

    }
    public static int getMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
}
