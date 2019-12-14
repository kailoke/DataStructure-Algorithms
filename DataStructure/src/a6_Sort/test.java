package a6_Sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class test {
    public test() {
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr = new int[8000000];
        for(int i = 0; i < arr.length; ++i) {
            arr[i] = (int)(Math.random() * 8000000.0D);
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        shellSort2(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    public static void shellSort(int[] arr) {
        for(int gap = arr.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < arr.length; ++i) {
                for(int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

    }

    public static void shellSort2(int[] arr) {
        for(int gap = arr.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < arr.length; ++i) {
                int j = i;
                int temp = arr[i];
                if (arr[i] < arr[i - gap]) {
                    while(j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
