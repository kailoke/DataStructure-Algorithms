package a1_Array;

import java.util.Arrays;
import java.util.StringTokenizer;

public class A3_ReverseArray {
    public static void main(String[] args) {
        int[] arr = {2,7,6,32,5};
        System.out.println("origin  :" + Arrays.toString(arr));
        reverseArray1(arr);
        System.out.println("reverse1:" + Arrays.toString(arr));

        reverseArray2(arr);
        System.out.println("reverse2:" + Arrays.toString(arr));

        StringTokenizer st = new StringTokenizer("www.baidu.com", ".b");
        while(st.hasMoreElements()) {
            System.out.println("Token:" + st.nextToken());
        }
    }

    // 索引
    public static void reverseArray1(int[] arr){
        int l = arr.length;
        for (int i = 0; i < l / 2; i++) {
            arr[i] = arr[i] ^ arr[l-i-1];
            arr[l-i-1] = arr[i] ^ arr[l-i-1];
            arr[i] = arr[i] ^ arr[l-i-1];
        }
    }

    // 指针
    public static void reverseArray2(int[] arr) {
        int rear = arr.length - 1;
        int front = 0;
        while (front < rear) {
            arr[front] = arr[front] + arr[rear];
            arr[rear] = arr[front] - arr[rear];
            arr[front] = arr[front] - arr[rear];
            front++;
            rear--;
        }
    }
}
