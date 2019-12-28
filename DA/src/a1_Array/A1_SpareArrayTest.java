package a1_Array;

import org.jetbrains.annotations.NotNull;
import java.util.*;

/*
    稀疏数组：将稀少值保存到具有稀疏数组特性的数组中
        row     col      value
 <0>    数组行   数组列   稀少值个数   * 稀疏数组的第一行：元数组行列+稀少值个数
 <n>    n所在行  n所在列  n值

    ·稀少值个数 == spareArray.length - 1，可考虑稀疏数组第一行第三列存储非稀疏值
 */

public class A1_SpareArrayTest {
    public static void main(String[] args) {
        // 创建二维数组
        int[][] arr = initializeArr();
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("********************************");

        // 生成稀疏数组
        int[][] spareArray = zipArr(arr);
        // 还原数组
        int[][] arrAnother = unZipArr(spareArray);

        // 对比数组差异
        System.out.println("********************************");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
            System.out.println(Arrays.toString(arrAnother[i]));
            System.out.println();
        }
    }

    /**
     * 随机初始化 大部分元素相同(==0)的二维数组
     */
    private static int[][] initializeArr(){
        int[][] arr = new int[11][10];
        int arrCount = 0;   // 控制随机生成的二维数组中稀疏值的个数
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arrCount < 40){
                    int temp = (int)(Math.random() * 3); // [min,max]区间的随机数 ：Math.random()*(max-min+1) + min
                    if (arrCount % 4 == 0){ // 使稀疏值散列
                        arr[i][j] = temp;
                    }
                    if (temp != 0){arrCount++;}
                }else{
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    /**
     * 以稀疏数组来保存二维数组数据
     * @param arr   二维数组
     * @return  稀疏数组
     */
    @NotNull
    private static int[][] zipArr(@NotNull int[][] arr){
        // 遍历二维数组，统计各值个数并计算稀疏值个数 (此处假定已知晓二维数组中元素仅有 0、1、2)
        // 考虑应对未知的二维数组，使用ArrayList1记录查找到的不同值，使用ArrayList2记录每个值的个数，应分析该二维数组是否可稀疏存储
        int[] valueCount = new int[3];
        for (int[] ints : arr) {
            for (int anInt : ints) {
                switch (anInt){
                    case 0:
                        valueCount[0]++;
                        break;
                    case 1:
                        valueCount[1]++;
                        break;
                    case 2:
                        valueCount[2]++;
                        break;
                }
            }
        }
        // 假定已知 0 为最多的重复数据
        int spareNum = valueCount[1] + valueCount[2];
        System.out.println("稀疏值个数: " + spareNum);

        // 初始化稀疏数组：(稀疏个数+1)行 * 3列
        int[][] sparseArray = new int[spareNum+1][3];
        // 第一行数组元数据
        sparseArray[0][0] = arr.length;
        sparseArray[0][1] = arr[0].length;
        sparseArray[0][2] = spareNum;

        // padding spareArray
        int line = 0;      // nextLine to input data
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0){
                    line++;
                    sparseArray[line][0] = i;
                    sparseArray[line][1] = j;
                    sparseArray[line][2] = arr[i][j];
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
        // initialize array
        int[][] arr = new int[spareArray[0][0]][spareArray[0][1]];


        // set default value to array 可以将默认值作为参数，达到指定值的效果
         for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 0;
            }
        }
        // padding
        for (int i = 1; i < spareArray.length; i++) {
            arr[spareArray[i][0]][spareArray[i][1]] = spareArray[i][2];
        }
        return arr;
    }
}
