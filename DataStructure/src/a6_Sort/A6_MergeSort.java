package a6_Sort;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/*
    归并排序： 分治策略
 */
public class A6_MergeSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8,9,1,7,2,3,5,4,6,0};
        arr1 = new int[]{5,4,3,2,1,0};
        mergeSort(arr1);
        System.out.println("arr1: " + Arrays.toString(arr1));
    }

    public static void mergeSort(@NotNull int[] arr){
        if (arr.length <= 1) {
            System.out.println("????");
            return;
        }
        sort(arr,0,arr.length-1);
    }

    public static int[] sort(int[] arr,int low,int high){
        if (low == high){
            return Arrays.copyOfRange(arr,low,low+1);
        }
        int division = (high - low + 1) / 2;
        sort(arr, low, high-low == 1 ? low : division-1);
        sort(arr,high-low == 1 ? high : division,high);
        int[] temp = new int[(high - low + 1)];
        int k = 0;
        if (low == division){
            division += 1;
        }
        int index = division-1;
        while (low < division){
            if (index < high){
                ++index;
                if (arr[low] <= arr[index]){
                    temp[k++] = arr[low];
                    low++;
                    break;
                }else {
                    temp[k++] = arr[index];
                }
            }
        }
        System.arraycopy(temp,0,arr,low,temp.length);
        return arr;
    }
}
