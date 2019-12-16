package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    希尔排序： 插入排序的改进版本，缩小增量插入排序
    > 1. 缩小增量，逐步减少分组数量直至1组
    > 2. 每次都对所有分组使用插入排序

    优势：每组修改量从小到大的过程中，整体趋近有序，从而大大减少交互次数
    希尔排序善于处理 趋近有序的数组
 */
public class A4_ShellSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8,9,1,7,2,3,5,4,6,0};
        shellSort3(arr1);
        System.out.println("arr1:" + Arrays.toString(arr1));

        int[] arr2 = new int[80000];
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


    // 分组直接插入法 ~= 16ms
    public static void shellSort1(int[] arr){
        for(int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从每个分组第二个开始循环插入排序
            for(int i = gap; i < arr.length; i++) {
                int insert = arr[i];
                int index;
                for (index = i-gap; index >= 0 && insert < arr[index] ; index-=gap) {
                    arr[index+gap] = arr[index];
                }
                // 意义不大，反而增加了判断次数
//                if (j+gap != i){
                arr[index + gap] = insert;
//                }
            }
        }
    }

    // 冒泡交换
    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            // 增量减小，每组组员增多
            for (int i = gap; i < arr.length; i++) {
                // 冒泡排序...
                for (int j = i - gap; j >=0 ; j-=gap) {
                    if (arr[j] > arr[j + gap]){
                        arr[j] = arr[j] ^ arr[j+gap];
                        arr[j+gap] = arr[j] ^ arr[j+gap];
                        arr[j] = arr[j] ^ arr[j+gap];
                    }
                }
            }
        }
    }

    // 选择交换
    public static void shellSort3(int[] arr){
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            // 增量减小，每组组员增多
            for (int i = 0; i < arr.length - 1; i++) {
                int min = i;
                for (int j = i + gap; j < arr.length; j += gap) {
                    // 每次和当前最值(index)做比较
                    if (arr[j] < arr[min]){
                        min = j;
                    }
                }
                if (min !=i ){
                    arr[i] = arr[i] ^ arr[min];
                    arr[min] = arr[i] ^ arr[min];
                    arr[i] = arr[i] ^ arr[min];
                }
            }
        }
    }
}
