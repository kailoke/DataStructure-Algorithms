package a1_Array;

/*
    寻找数组中第二小的值
 */
public class A2_SearchSecondMin {
    public static void main(String[] args) {
        int[] arr = {4,7,2,-3,10};
        System.out.println("second Min：" + getSecondMin(arr));

    }

    public static int getSecondMin(int[] arr) {
        // 假设第1个最小，第二小的不存在
        int min = 0, secondMin = -1;
        for (int i = 1; i < arr.length; i++) {
            // 1. 若当前值<min，则secondMin=之前min，更新min为当前值
            if (arr[i] < arr[min]){
                 secondMin = min;
                 min = i;
             // 2.1 secondMin不存在时，第一个进行比较的即secondMin
            // 2.2 有secondMin时，若当前值比最小值大且比secondMin小，则更新secondMin
            }else if (secondMin == -1 || arr[i] < arr[secondMin]) {
                secondMin = i;
            }
        }
        return arr[secondMin];
    }
}
