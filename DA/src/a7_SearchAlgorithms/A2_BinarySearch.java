package a7_SearchAlgorithms;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Arrays;

/*  BinarySearch
    二分查找：对目标有序数组快速 二分
    > 1. 传入数组必须有序
    > 2. 退出递归的条件:  2.1 成功匹配     2.2 归到最后没有匹配
    > 3. 考虑多个匹配值，找到匹配值后，在其左右扫描相同值

    ** 二分递归查找算法的的T : O(log2 n)
 */
public class A2_BinarySearch {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,1,2,3,4,4,5,5,5,5,5,5};
        int index1 = binarySearch1(arr1, 5);
        System.out.println("index1 :" + index1);

        ArrayList<Integer> index2 = binarySearch2(arr1, 5);
        System.out.println("index2 :" + index2);

    }

    public static int binarySearch1(@NotNull int[] arr, int value){
        if (arr.length <= 1){
            System.out.println("sss");
            return -1;
        }
        return biSearch1(arr,0,arr.length - 1,value);
    }

    /**
     * 递归二分，查找到值就返回索引位置
     * @param arr 源数组
     * @param low 子数组起始索引
     * @param high 子数组最大索引
     * @param value 查找值
     * @return 索引位置,-1代表没找到
     */
    private static int biSearch1(int[] arr, int low, int high, int value){
        // 值不存在时，直接返回-1
        if (low > high){
            return -1;
        }
        // 2分查找
        int middle = (low + high) >>> 1;
        if (arr[middle] < value){
            return biSearch1(arr,middle + 1, high, value);
        }else if (arr[middle] > value){
            return biSearch1(arr, low, middle - 1, value);
        }else {
            return middle;
        }
    }

    public static ArrayList<Integer> binarySearch2(@NotNull int[] arr, int value){
        if (arr.length <= 1){
            System.out.println("sss");
        }
        return biSearch2(arr,0, arr.length-1, value);
    }
    // 查找所有相同值，返回值索引位置的集合
    private static ArrayList<Integer> biSearch2(int[] arr, int low, int high, int value){
        // 值不存在时，返回有 -1 的动态数组
        if (low > high || value < arr[low] || value > arr[high]){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(-1);
            return list;
        }
        // 二分查找
        int middle = (low + high) / 2;
        if (arr[middle] < value){
            return biSearch2(arr,middle + 1, high, value);
        }else if (arr[middle] > value){
            return biSearch2(arr, low, middle - 1, value);
        // 左右扫描是否还有相同值
        }else {
            ArrayList<Integer> list = new ArrayList<>();
            // 使用辅助指针temp 定位重复值得最左侧索引
            int temp = middle - 1;
            while ( temp >= 0 && arr[temp] == arr[middle]) {
//                list.add(temp);
                temp--;
            }
            // temp += 1 即为最左侧值索引，当其 != middle时，证明middle左侧还有相同数据
            // 处理左侧数据
            if ((temp += 1) != middle){
                while (temp<middle){
                    list.add(temp++);
                }
            }
            // 从middle开始，及处理右侧数据
            temp = middle;
            while ( temp < arr.length && arr[temp] == arr[middle]) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
