package a11_Algorithms;

/*
    二分查找，不用递归实现：
    1.头尾指针，二分指针
    2.当头指针<=尾指针时，循环二分改变二分指针的值，从而修改下一次循环头尾指针的值
 */
public class A1_BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr1 = {1,3,8,10,11,67,100};
        int i = binarySearch(arr1, 100);
        System.out.println("index = " + i);
    }

    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (value < arr[mid]) {
                right = mid - 1;
            }else if (value > arr[mid]) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
