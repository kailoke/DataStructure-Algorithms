package a6_Sort;

import java.time.Instant;
import java.util.Arrays;

/* 选择排序的一种
  堆排序: 利用堆数据结构(顺序存储二叉树)设计的排序算法
  一、堆是具有以下性质的"完全二叉树"：
    > 1.大顶堆(升序)：每个节点的值大于或等于其左右子节点的值，不要求左右子节点值的大小顺序
        arr[i] >= arr[2*i+1] %% arr[i] >= arr[2*i+2]
    > 2.小顶堆(降序)：每个节点的值小于或等于其左右子节点的值，不要求左右子节点值的大小顺序
        arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
    > 3.顺序存储二叉树的最后一个内节点(非叶子节点)索引 : arr.length/2 - 1 == ((arr.length-1) - 1) / 2
        ↑↑ 最后一个叶子节点的父节点索引：(index-1) / 2 == arr.length/2 - 1
  二、堆排序思想：
    > 1. 将待排序数组构造成 (大小)顶堆树 ==> 顺序存储的二叉树
    > 2. 顶堆即最值，将其与末尾元素(arr.length-1)--交换
    > 3. 循环构建剩余未排序的数组成堆，循环将根节点和选择的排序位置进行值交换
 */
public class A8_HeapSort {
    public static void main(String[] args) {
        int[] arr1 = {4,5,6,8,9,-1,2,40,11,56};
        heapSort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[8000000];   // 800万 ~= 2100ms
        for (int i = 0; i < arr2.length-1; i++) {
            arr2[i] = (int) (1+ Math.random()*8000000); // [1-800万]
        }
        arr2[arr2.length-1] = 1;
        Instant time1 = Instant.now();
        heapSort(arr2);
        Instant time2 = Instant.now();
        System.out.println("time:" + (time2.toEpochMilli() - time1.toEpochMilli()));
//        System.out.println(Arrays.toString(arr2));
        A0_SortedCheck.sortedCheck(arr2);
    }

    // 堆排序：首次重构堆后，从根节点开始重构
    public static void heapSort(int[] arr) {
        // 首次重构堆: 从最后一个内节点索引开始 i = ((arr.length-1) - 1)/2
        // 逆序调整所有内节点，使每一个内节点称为子顶堆树
        for (int i = arr.length/2 -1; i >= 0 ; i--) {
            toBigHead(arr, i, arr.length);
        }
        // 从最后一个序号开始和根节点交换数值，直到数组第二个位置交换完毕
        for (int i = arr.length-1; i > 0 ; i--) {
            arr[0] = arr[0] ^ arr [i];
            arr[i] = arr[0] ^ arr [i];
            arr[0] = arr[0] ^ arr [i];                    //    (new arr.length)
            // 之后从根节点开始重构，仅需将根节点交换到其最小位置    （      i     ）
            // 已成功完成一次选择，则下次重构length = 原长度 - 1  即 arr.length -1 +1 -1,
            toBigHead(arr,0, i);
        }
    }

    /** 从最后一个非叶子节点开始，从左至右，从下至上进行调整所有的非叶子节点
     *  将非叶子节点调整为 满足大顶堆条件的子树
     * @param arr   待调整数组
     * @param i     非叶子节点在数组中的索引
     * @param length    对多少个元素进行调整，length逐渐减少
     */
    public static void toBigHead(int[] arr,int i, int length){
        int temp = arr[i];
        // 当前节点的左子节点index = 2*i+1   子节点(j || j++)的左子节点index = 2*index+1
        for (int j = i*2+1; j < length; j = j*2+1) {
            // 获得当前节点的较大子节点 索引，并以较大子节点索引为for循环基础展开遍历
            if (j+1 < length && arr[j] < arr[j+1]){
                j++;
            }
            // 当前节点 < 较大子节点
            if ( temp < arr[j]) {
                arr[i] = arr[j];    // 将较大的子节点值 赋给 当前节点
                i = j;              // 更新当前节点的 索引位置信息至被替换的位置
            }else {
                break;  // 因为逆序，其所有子节点已经是顶堆子树；当自身后继节点也是顶堆树时，整个子树也是顶堆树
            }
            // 因有其他值成为当前节点子节点的新父节点值，需要继续循环对比，确认其子树全部是顶堆子树
        }
        // 更新最后的被替换位置值为 当前节点值，完成当前节点值的移动
        arr[i] = temp;
    }
}
