package a5_Recursion;

import java.util.Arrays;

/*
    迷宫问题
    1.假定此格子能走，之后尝试向各个方向行进
    2.失败一方向则回溯重新寻找方向
    3.成功则继续加丁格子能走，递归
 */
public class MazeProblem {
    public static void main(String[] args) {
        // 初始化地图 0:可走，1:墙，2:正常通行，3:已走但不通
        int[][] map = new int[8][7];
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 1; i < map[0].length - 1; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("*******************************");


        setWay(map,1,1);

        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }

    static boolean setWay(int[][] map,int row ,int col){
        // 结束点
        if (map[map.length-2][map[0].length-2] == 2){
            return true;
        }else{
            // 当前点未走
            if (map[row][col] == 0){
                map[row][col] = 2;
                if (setWay(map,row+1,col)){ // 下
                    return true;
                }else
                if (setWay(map,row,col+1)){ // 右
                    return true;
                }else
                if (setWay(map,row-1,col)){ // 上
                    return true;
                }else
                if (setWay(map,row,col-1)){ // 左
                    return true;
                }else {
                    map[row][col] = 3;
                    return false;
                }
            }else {
                // 其他可能是 1,2,3
                return false;
            }
        }
    }
}
