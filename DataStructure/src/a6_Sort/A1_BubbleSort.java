package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    冒泡排序：
    > 1.相邻元素逆序则交换
    > 2.每次冒泡都找出了 相邻比较符 的最值
    > 3.当某次冒泡时整个数组都没有发生交换，则数组已经有序，可提前结束
 */
public class A1_BubbleSort {
    public static void main(String[] args) {
        int[] arr1 = {3,9,-2,10,-1};
        bubbleSort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        Instant time1 = Instant.now();
        bubbleSort(arr2);
        Instant time2 = Instant.now();

        System.out.println(time2.toEpochMilli() - time1.toEpochMilli());  // ~=12400
        System.out.println(Arrays.toString(arr2));
    }

    public static void bubbleSort(int[] arr){
        boolean exchange;
        for (int i = 0; i < arr.length-1; i++) {
            exchange = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]){
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                    exchange = true;
                }
            }
            if (!exchange){
                break;
            }
        }
    }
}
