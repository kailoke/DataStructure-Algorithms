package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    希尔排序： 插入排序的改进版本，缩小增量排序

 */
public class A4_ShellSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8,9,1,7,2,3,5,4,6,0};
        shellSort3(arr1);
        System.out.println("arr1:" + Arrays.toString(arr1));

        int[] arr2 = new int[800];
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        shellSort3(arr2);
        Instant time2 = Instant.now();
        System.out.println(time2.toEpochMilli() - time1.toEpochMilli());
        System.out.println(Arrays.toString(arr2));
    }

    // 交换法  6000ms?
    public static void shellSort1(int[] arr){
        int gap = arr.length;
        // 增量减少=>分组数量减少
        while ( (gap/=2) > 0){
            // 组内数量增多，倒序按增减寻找组员
            for (int i = gap; i < arr.length; i++) {
//            for (int i = arr.length-gap; i < arr.length; i++) {
                // 比较组内相邻成员是否需要交换
                for (int j = i - gap; j >=0 ; j-=gap) {
                    if (arr[j] > arr[j+gap]){
                        arr[j] = arr[j] ^ arr[j+gap];
                        arr[j+gap] = arr[j] ^ arr[j+gap];
                        arr[j] = arr[j] ^ arr[j+gap];
                    }
                }
            }
        }
//        A3_InsertSort.insertSort2(arr);
    }

    public static void shellSort2(int[] arr){
        int gap = arr.length;
        // 增量减少=>分组数量减少
        while ( (gap/=2) > 0){
            // 组内数量增多，倒序按增减寻找组员
            for (int i = arr.length-gap; i < arr.length; i++) {
                // 比较组内相邻成员是否需要交换
                for (int j = i - gap; j >=0 ; j-=gap) {
                    if (arr[j] > arr[j+gap]){
                        arr[j] = arr[j] ^ arr[j+gap];
                        arr[j+gap] = arr[j] ^ arr[j+gap];
                        arr[j] = arr[j] ^ arr[j+gap];
                    }
                }
            }
        }
        A3_InsertSort.insertSort2(arr);
    }

    // 直接插入法
    public static void shellSort3(int[] arr){
        for(int gap = arr.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while(j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
//        A3_InsertSort.insertSort2(arr);
    }
}
