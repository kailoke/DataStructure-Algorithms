package a7_SearchAlgorithms;

import java.util.ArrayList;

/*  SequentialSearch
    线性查找，完整遍历整个数组查找目标
 */
public class A1_SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,9,11,-1,34,89};  // 无序数组
        ArrayList<Integer> seqsearch = seqsearch(arr, 11);
        if (seqsearch.size() == 0){
            System.out.println("没有找到数据");
        }else {
            System.out.println("查找到的位置：" + seqsearch.toString());
        }
    }

    public static ArrayList<Integer> seqsearch(int[] arr,int value){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
               list.add(i);
            }
        }
        return list;
    }
}
