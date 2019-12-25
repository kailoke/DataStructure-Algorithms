package a7_SearchAlgorithms;

import org.jetbrains.annotations.NotNull;

/*  InsertSearch
   插值查找：二分查找的基础上让中轴值根据数组分布状态自适应
   > mid = 起始点 + 区间索引长度 * (插值-起始点值范围)/区间值范围
   > ↑ 按插值所占整体数组的百分比划分区间长度

   适合用于元素分布呈较平滑线性的数据，即边际系数变化不大
 */
public class A3_InsertSearch {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,1,2,3,4,4,5,5,5,5,5,5};
        int index1 = insertSearch(arr1, 5);
        System.out.println("index1 :" + index1);
    }

    public static int insertSearch(@NotNull int[] arr,int value){
        return insertSearch(arr,0,arr.length-1, value);
    }

    private static int insertSearch(int[] arr, int low, int high, int value) {
        if (low > high && value < arr[low] && value > arr[high]){
            return -1;
        }
        int mid = low + (high - low) * (value - arr[low]) / (arr[high] - arr[low]);
        if (value < arr[mid]){
            return insertSearch(arr, low,mid-1, value);
        }else if (value > arr[mid]){
            return insertSearch(arr,mid+1, high, value);
        }else {
            return mid;
        }
    }
}
