package a5_Recursion;

import java.util.Arrays;

/*
    使用一维数组表示8皇后的结果集
    > 数组索引：第 索引+1 个皇后
    > 索引值 ： 第 索引+1 个皇后放在 (索引+1行,索引值+1列)
    > 从第0行开始遍历，递归查找到不冲突的方案则打印，否则回溯至上一步继续，直到每行的所有均被尝试
 */
public class A3_Queen8 {
    private static int count = 0;
    public static void main(String[] args) {
        int[] map = new int[8];
        queen8(map,0);
    }

    /**
     * 寻找满足八皇后的所有解法
     * @param map 结果集
     * @param q 放入的皇后编号(实际= q+1)
     */
    public static void queen8(int[] map, int q){
        if (q == map.length) {
            System.out.println("result " + (++count) + ":" + Arrays.toString(map));
            return;
        }
        for (int i = 0; i < map.length; i++) {
            map[q] = i;
            if (!conflict(map,q)){
                queen8(map,q + 1);
            }
        }
    }

    /**
     * 判断当前皇后放入后是否和其他所有皇后冲突
     * @param q 当前皇后序号
     * @return true:conflicted
     */
    private static boolean conflict(int[] map, int q) {
        for (int i = 0; i < q; i++) {
            // 同列
            if (map[q] == map[i] ||
                    // 斜线
                    Math.abs(q-i) == Math.abs(map[q]-map[i])) {
                return true;
            }
        }
        return false;
    }
}
