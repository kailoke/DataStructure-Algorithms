package a11_Algorithms;

import java.util.Arrays;

/*  动态规划算法：与分治算法类似，核心思想是 将大问题划分为小问题进行解决，从而一步步获取最优解的处理方法
        > 与分治算法不同的是，动态规划分解的子问题往往"不是互相独立"的，即下一个子阶段的求解是建立在上一个子阶段的解的基础上的
        > 动态规划可以通过填表的方式逐步推进，得到最优解

 */
public class A3_DynamicProgramming {
    public static void main(String[] args) {
        int[] weight = {1,4,3};         // 物品重量
        int[] value = {1500,3000,2000}; // 物品价值
        int m = 4;                      // 背包的容量
        int n = value.length;           // 物品的总个数

        // 创建二维数组(作为表待填入)
        // table[i][j] 表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] table = new int[n+1][m+1];

        // 初始化第一列和第一行设置为0
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 0;
        }
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = 0;
        }

        // 动态规划
        // 不处理第一行
        for (int i = 1; i < table.length; i++) {
            // 不处理第一列   J即为背包的渐进重量
            for (int j = 1; j < table[0].length; j++) {
                if (weight[i-1] > j){   // 第一个元素从0开始
                    table[i][j] = table[i-1][j];
                }else {                 // weight[i-1] <= j
                    // 容量足够时，取max(上一个方案价值, 新方案+剩余空间的方案价值)
                    table[i][j] = Math.max(table[i-1][j], value[i-1] + table[i-1][j-weight[i-1]]);
                }
            }
        }





        for (int[] ints : table){
            System.out.println(Arrays.toString(ints));
        }
    }

    /*
    背包问题：一个给定容量的背包，若干具有一定价值和重量的物品，如何选择物品放入背包使物品的价值最大
    分为01背包(物品不重复)和完全背包(物品可重复)
     */
}
