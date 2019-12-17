package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/*
    堆排序: 自然排序：大顶堆   降序排序：小顶堆
    > 1. 将目标数组重建为 顶堆树 ==> 顺序存储的二叉树
        > ↑↑ 获得"完全二叉树"的最后一个非叶子节点索引 : arr.length/2 - 1
    > 2. 将最大值交换到新的队尾，之后从头开始循环重建直到所有最大值被遍历

    大顶堆：每个节点的值都 >= 左右子节点的值  : arr[i] >= arr[2*i+1] && arr[i] >=arr [2*i+2]
    小顶堆：每个节点的值都 =< 左右子节点的值  : arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
 */
public class A8_HeapSort {
    public static void main(String[] args) {
        int[] arr1 = {4,5,6,8,9,-1,2,56,11};
        heapSort2(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[8000000];   // 800万 ~= 2100ms
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        heapSort1(arr2);
        Instant time2 = Instant.now();
        System.out.println("time:" + (time2.toEpochMilli() - time1.toEpochMilli()));
//        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }
    // 标准写法，一次大重构后，从根节点开始循环重构
    public static void heapSort1(int[] arr) {
        // 第一次重构整个堆
        for (int i = arr.length/2 -1; i >= 0 ; i--) {
            toBigHead(arr, i, arr.length);
        }
        // 从最后一个序号开始交换数值，直到index==0结束
        for (int i = arr.length-1; i > 0 ; i--) {
            arr[0] = arr[0] ^ arr [i];
            arr[i] = arr[0] ^ arr [i];
            arr[0] = arr[0] ^ arr [i];
            toBigHead(arr,0, i);    // 从根节点开始
            // 上述已成功交换一次，则下次length = 原长度 - 1  即 arr.length -1 +1 -1,
        }
    }

    // 自我尝试写法，每次都在遍历，蠢
    public static void heapSort2(int[] arr) {
        // 需要调整的次数 arr.length - 1
        for (int i = arr.length - 1; i > 0 ; i--) {
            // 1.重构 堆
            for (int j = (i+1)/2 - 1; j >= 0; j--) {
                toBigHead(arr, j, i+1);
            }
            // 2.交换最大值到新队尾
            arr[0] = arr[0] ^ arr [i];
            arr[i] = arr[0] ^ arr [i];
            arr[0] = arr[0] ^ arr [i];
        }
    }

    /**
     *  将非叶子节点调整为 满足大顶堆条件的子树
     * @param arr   待调整数组
     * @param i     非叶子节点在数组中的索引
     * @param lenght    对多少个元素进行调整，length逐渐减少
     */
    public static void toBigHead(int[] arr,int i, int lenght){
        int temp = arr[i];
        // 从左子节点开始遍历
        for (int j = i*2+1; j < lenght; j = j*2+1) {
            // 寻找最大的子节点
            if (j+1 < lenght && arr[j] < arr[j+1]){
                j++;
            }
            // 非叶子节点 不是最大
            if ( temp < arr[j]) {
                arr[i] = arr[j];    // 将最大的子节点值赋给非叶子节点
                i = j;              // 标记此子节点，循环搭建以其子节点为父节点的子树堆
            }else {
                break;  //叶子节点最大，传入的非叶子节点一定没有更多的子节点了
            }
        }
        // 调整arr[index] 至其最小的位置
        arr[i] = temp;
    }
}
