package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    选择排序：
    > 1.每轮"选择"目标位置值为最值，从下一个开始遍历出 真正最值的位置
    > 2.将目标位置值和最值进行交换

    相比冒泡，减少了改值次数，但比较次数依然多
 */
public class A2_SelectSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{5,-3,7,11,9,0,4};
        selectSort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        Instant time1 = Instant.now();
        selectSort(arr2);
        Instant time2 = Instant.now();
        System.out.println(time2.toEpochMilli() - time1.toEpochMilli());  // ~=2900
    }

    public static void selectSort(int[] arr){
        int indexMin;
        for (int i = 0; i < arr.length-1; i++) {
            indexMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 和当前最小位置值做比较
                if (arr[j] < arr[indexMin]) indexMin = j;
            }
            if (indexMin != i){
                arr[i] = arr[i] + arr[indexMin];
                arr[indexMin] = arr[i] - arr[indexMin];
                arr[i] = arr[i] - arr[indexMin];
            }
        }
    }
}
