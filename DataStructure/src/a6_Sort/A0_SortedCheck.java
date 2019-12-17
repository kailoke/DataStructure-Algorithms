package a6_Sort;

public class A0_SortedCheck {
    public static void sortedCheck(int[] arr){
        for (int i = 1,min = arr[0]; i < arr.length; i++) {
            if (arr[i] >= min){
                min = arr[i];
            }
            else {
                System.out.println("error:" + i);
            }
        }
    }
}
