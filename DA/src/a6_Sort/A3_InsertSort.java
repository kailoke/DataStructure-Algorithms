package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/* 基本思想：将待排序的数据对象看成 有序表 和 无序表，将无序表中的元素从有序表后方顺次插入到有序表中
    插入排序: 将待插入值插入 有序元素中的有序位置
    > 0.位置0默认有序，从位置1开始，找到其所属的插入位置
    > 1.每轮缓存"待插入值"，使用辅助遍历指针与之前的元素做比较并循环向前移动
    >   直到遇到有序元素，停止比较
    > 2.在 停止位置+1 放入缓存值

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
        insertSort1(arr2);
        Instant time2 = Instant.now();
        System.out.println(time2.toEpochMilli() - time1.toEpochMilli());
        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }

    // ~=3600   自写插入排序，从头与有序元素比较，与有序元素的交互次数过多
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
            // 缓存当前值
            int temp = arr[i];
            // 辅助遍历数组指针 [0,i-1]
            int index = i - 1;
            while (index >= 0 && temp < arr[index]){
                // 交换辅助指针的值至其后一个位置
                arr[index + 1] = arr[index];
                // 向前遍历
                index--;
            }
            // 辅助指针移动过，省略计算和判断次数反而提高效率...
            if (index != i - 1){
                // 此时辅助指针停留的是有序元素位置，在其后方放入temp
                arr[index + 1] = temp;
            }
        }
    }

    // 重写while部分
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
//            if (j != i - 1){
                arr[j+1] = temp;
//            }
        }
    }
}
