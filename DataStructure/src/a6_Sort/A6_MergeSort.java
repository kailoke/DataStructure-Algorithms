package a6_Sort;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Arrays;

/*
    归并排序： 分治策略
    > 1. 递归均分数组，直至字数组元素仅剩余1个
    > 2. 从底部开始，被分开的两个数组开始合并到临时数组中
    > 3. 将临时数组中的排序数组替换原被分组数组
 */
public class A6_MergeSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8,4,5,7,1,3,6,2};
//        arr1 = new int[]{5,4,3,2,1,0};
        mergeSort(arr1);
        System.out.println("arr1: " + Arrays.toString(arr1));

        int[] arr2 = new int[8000000];   // 800万 ~= 2000ms
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        mergeSort(arr2);
        Instant time2 = Instant.now();
        System.out.println("time:" + (time2.toEpochMilli() - time1.toEpochMilli()));
//        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }

    public static void mergeSort(@NotNull int[] arr){
        if (arr.length <= 1) {
            System.out.println("????");
            return;
        }
        int[] temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
    }

    private static void sort(int[] arr,int low,int high,int[] temp){
        if (low >= high){
            return;
        }
        // 分
        int division = (low + high) / 2;
        sort(arr, low, division, temp);
        sort(arr, division + 1, high, temp);

        // 治
//        int[] temp = new int[high-low + 1];   // 将temp数组放于堆空间，减少temp的创建和回收次数
        int t = 0;
        int left = low;
        int right = division + 1;
        // 将某一边全小的数值顺序填入temp
        while (left <= division && right <= high){
                if (arr[left] < arr[right]){
                    temp[t++] = arr[left++];
                }else {
                    temp[t++] = arr[right++];
                }
        }
        // 处理剩余可能的未处理值
        while (left <= division){
            temp[t++] = arr[left++];
        }
        while (right <= high){
            temp[t++] = arr[right++];
        }
        // 将排序好的数值复制进原数组
        System.arraycopy(temp,0,arr,low, high-low + 1);
    }
}
