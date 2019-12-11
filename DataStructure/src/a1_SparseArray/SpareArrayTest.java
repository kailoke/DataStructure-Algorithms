package a1_SparseArray;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
/*
    稀疏数组：将稀少值保存到具有稀疏数组特性的数组中
        row     col      value
 <0>    数组行   数组列   稀少值个数
 <n>    n所在行  n所在列  值
 */
public class SpareArrayTest {
    public static void main(String[] args) {
        int[][] arr = initializeArr();
        System.out.println("********************************");

        int[][] spareArray = zipArr(arr);
        System.out.println("********************************");

        int[][] arrAnother = unZipArr(spareArray);
    }

    @NotNull
    private static int[][] initializeArr(){
        // random TwoDimension Array with 11 * 10
        // values: 0、1、2
        int[][] arr = new int[11][10];
        int arrCount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arrCount < 30){
                    int temp = (int)(Math.random()*3);
                    if (arrCount % 3 ==0){
                        arr[i][j] = temp;
                    }
                    if (temp == 0){arrCount++;}
                }else{
                    arr[i][j] = 0;
                    arrCount++;
                }
            }
        }
        System.out.println("arrCount: " + arrCount);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        return arr;
    }

    @NotNull
    private static int[][] zipArr(@NotNull int[][] arr){
        // count values
        int[] countList = new int[3];
        for (int[] ints : arr) {
            for (int anInt : ints) {
                switch (anInt){
                    case 0:
                        countList[0]++;
                        break;
                    case 1:
                        countList[1]++;
                        break;
                    case 2:
                        countList[2]++;
                        break;
                }
            }
        }

        // suppose '0' counts the most
        int sum = countList[1] + countList[2];
        System.out.println("sum: " + sum);
        // initialize SparseArray
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = arr.length;
        sparseArray[0][1] = arr[0].length;
        sparseArray[0][2] = sum;

        // padding
        int s = 0;      // nextLine
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0){
                    s++;
                    sparseArray[s][0] = i;
                    sparseArray[s][1] = j;
                    sparseArray[s][2] = arr[i][j];
                }
            }
        }
        for (int[] ints : sparseArray) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("SparseArrays' lines: " + sparseArray.length);
        return sparseArray;
    }

    @NotNull
    private static int[][] unZipArr(@NotNull int[][] spareArray){
        // initialize arr
        int[][] arr = new int[spareArray[0][0]][spareArray[0][1]];
        // set default value 可以将默认值作为参数，达到指定值的效果
         for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 0;
            }
        }
        // padding
        for (int i = 1; i <= spareArray[0][2]; i++) {
            arr[spareArray[i][0]][spareArray[i][1]] = spareArray[i][2];
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        return arr;
    }
}
