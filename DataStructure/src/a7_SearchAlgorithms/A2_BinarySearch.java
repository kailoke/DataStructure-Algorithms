package a7_SearchAlgorithms;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

/*
    二分查找：对目标有序子数组快速 对半分组
    > 1. 传入数组必须有序
    > 2. 退出递归的条件:  2.1 成功匹配     2.2 没有匹配
    > 3. 复数个匹配值，左右扫描相同值
 */
public class A2_BinarySearch {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,1,2,3,4,4,5,5,5,5,5,5};
        int index1 = binarySearch1(arr1, 5);
        System.out.println("index1 :" + index1);

        ArrayList<Integer> index2 = binarySearch2(arr1, 5);
        System.out.println("index2 :" + index2);

    }

    // 基础二分，找到即返回
    public static int binarySearch1(@NotNull int[] arr, int value){
        if (arr.length <= 1){
            System.out.println("sss");
            return -1;
        }
        return biSearch1(arr,0,arr.length - 1,value);
    }
    private static int biSearch1(int[] arr, int low, int high, int value){
        // 值不存在时，直接返回-1
        if (low > high){
            return -1;
        }
        // 2分查找
        int middle = (low + high) / 2;
        if (arr[middle] < value){
            return biSearch1(arr,middle + 1, high, value);
        }else if (arr[middle] > value){
            return biSearch1(arr, low, middle - 1, value);
        }else {
            return middle;
        }
    }

    // 查找所有相同值
    public static ArrayList<Integer> binarySearch2(@NotNull int arr[], int value){
        if (arr.length <= 1){
            System.out.println("sss");
        }
        return biSearch2(arr,0, arr.length-1, value);
    }
    private static ArrayList<Integer> biSearch2(int[] arr, int low, int high, int value){
        // 值不存在时，直接返回-1
        if (low > high){
            return new ArrayList<>();
        }
        // 2分查找
        int middle = (low + high) / 2;
        if (arr[middle] < value){
            return biSearch2(arr,middle + 1, high, value);
        }else if (arr[middle] > value){
            return biSearch2(arr, low, middle - 1, value);
        }else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = middle - 1;
            while ( temp >= 0 && arr[temp] == arr[middle]) {
//                list.add(temp);
                temp--;
            }
            // 增加扫描逻辑，使得返回的数组有序，上述temp为定位起始点
            if ((temp += 1) != middle){
                while (temp<middle){
                    list.add(temp++);
                }
            }

            // 处理middle及右侧
            temp = middle;
            while ( temp < arr.length && arr[temp] == arr[middle]) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
