package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    插入排序: 将待插入值插入 有序元素中的有序位置
    > 1.每轮缓存"待插入值"，与之之前的数组做比较并循环移动 区间数值
    > 2.若有插入位置，则在插入位置后方插入缓存值

    优势：减少与已有序元素的交互次数
 */
public class A3_InsertSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{5,-3,7,11,9,0,4};
        insertSort3(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[80000];
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        insertSort2(arr2);       // java有函数指针吗?
        Instant time2 = Instant.now();
        System.out.println(time2.toEpochMilli() - time1.toEpochMilli());
        System.out.println(Arrays.toString(arr2));
    }

    // ~=3600   自写插入，从头与有序元素比较，与有序元素的交互次数过多
    public static void insertSort1(int[] arr){
//        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]){
                    int temp = arr[i];
                    //System.arrayCopy(src,srcIndex,dest,destIndex,length);
                    System.arraycopy(arr, j, arr, j + 1, i - j);
                    arr[j] = temp;
                }
            }
        }
    }

    // ~= 700
    public static void insertSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int index = i-1;
            while (index >= 0 && temp < arr[index]){
                arr[index+1] = arr[index];
                index--;
            }
            if (index != i-1){
                arr[index+1] = temp;
            }

        }
    }

    // 重写while部分，因为元素已经有序，所以与有序元素的最值比较失败后即可跳出比较
    public static void insertSort3(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (;j >= 0;j--) {
                if (temp < arr[j]){
                    arr[j+1] = arr[j];
                }
                else{
                    break;
                }
            }
            if (j != i - 1){
                arr[j+1] = temp;
            }
        }
    }
}
