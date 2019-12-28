package a5_Recursion;

import java.util.Arrays;

/*
    迷宫问题
    1.假定此格子能走，走到此格子后，尝试向各个方向行进
    2.失败一方向则回溯，按设定的指令序列重新寻找方向
    3.成功则继续 假定 下一个格子能走，递归
 */

public class A2_MazeProblem {
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

        setWay(map,1,1);    // 设定起始点
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 递归寻找到终点
     * @param map 地图
     * @param row 起始点行坐标
     * @param col 起始点列坐标
     * @return true:找到通路，否则false
     */
    static boolean setWay(int[][] map,int row ,int col){
        // 结束点
        if (map[map.length-2][map[0].length-2] == 2){
            return true;
        }else{
            // 当前点可走，还没走
            if (map[row][col] == 0){
                map[row][col] = 2;      // 走过此点，认为此点是可到达终点的路径点
                if (setWay(map,row+1,col)){ // 下row+1
                    return true;
                }else
                if (setWay(map,row,col+1)){ // 右col+1
                    return true;
                }else
                if (setWay(map,row-1,col)){ // 上row-1
                    return true;
                }else
                if (setWay(map,row,col-1)){ // 左col-1
                    return true;
                }else { // 上下左右均返回false，则走过的此点没有可行道路，标记为3，返回false;递归回溯
                    map[row][col] = 3;
                    return false;
                }
            // 此点不是 默认0 的可行走且为行走的点
            }else {
                // 其他可能是 1,2,3：墙/走过/走过的死路，均返回false,递归回溯
                return false;
            }
        }
    }
}
