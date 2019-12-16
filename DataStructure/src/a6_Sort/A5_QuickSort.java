package a6_Sort;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Arrays;

/*
    快速排序:冒泡排序的改进版，理解为每次分组后 将无序的数据交换至有序位置
    > 1. 设置数组中轴值，按中轴值将数组分割，目前学习使用 子数组的第一个值作为中轴
    > 2. 右侧先开始扫描 <middle 的值，保证最后 middle 和 右指针停留值交换后，原右指针停留值交换至左侧
    > 3. 左侧后扫描 >middle 的值，找到值或扫描到右侧指针停止
    > 4. 最后 左右指针 同时位于中轴值点，即可交换 middle 和 停留点值
    > 5. 按中轴点切割数组，递归上述操作，直至数组中元素<=1个 , 即 if (low < high)
 */
public class A5_QuickSort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8,9,1,7,2,3,5,4,6,0};
        arr1 = new int[]{5,4,3,2,1,0};
        quickSort(arr1);
        System.out.println("arr1: " + Arrays.toString(arr1));

//        System.exit(0);
        int[] arr2 = new int[8000000];  // 800万 ~= 1800ms
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        quickSort(arr2);
        Instant time2 = Instant.now();
        System.out.println("time:" + (time2.toEpochMilli() - time1.toEpochMilli()));
//        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }

    public static void quickSort(@NotNull int[] arr) {
        if(arr.length <= 1){
            System.out.println("数据过少，无需排序");
        }
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] array, int low, int high) {
        if(low < high) {
            // 中轴值:middle
            int middle = array[low];
            // 定义左右扫描指针，left可能是最值，所以从Left开始扫描
            int left = low, right = high;
            while(left != right) {
                // 先从右边开始往左找，直到找到比base值小的数
                while(left < right && array[right] >= middle) right--;

                // 再从左往右边找，直到找到比base值大的数
                while(left < right && array[left] <= middle) left++;

                // 扫描结束表示找到了需要调整的值，或者 left==right 结束扫描
                if(left < right) {
                    swap(array, left, right);
                }
            }
            // 中轴值归位，left == right 恒成立
            if ( low != right){
                swap(array,low,right);
            }
//            array[low] = array[right];
//            array[right] = middle;
//            System.out.print("middle :" + middle + "\t");
//            System.out.println(Arrays.toString(array));

            // 递归，继续向基准的左右两边执行和上面同样的操作
            // i的索引处为上面已确定好的基准值的位置，无需再处理
            sort(array, low, right - 1);
            sort(array, right + 1, high);
        }
    }

    public static void swap(@NotNull int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}

