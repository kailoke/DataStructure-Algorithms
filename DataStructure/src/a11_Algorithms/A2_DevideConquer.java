package a11_Algorithms;

/*  分治算法 ： 分而治之
    > 把复杂的问题分解成两个或更多个相同或相似的子问题，再把子问题分成更小的问题······
      直到最后子问题可以简单的直接求解
      原问题的解即子问题的解的合并

    > 二分搜索
    > 归并排序
    > 快速排序
 */
public class A2_DevideConquer {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c) {
        // 仅有1个盘
        if (num == 1){
            System.out.println("第1个盘 " + a + "->" + c);
        // >=2个
        }else {
            // 最上面的盘 A -> B
            hanoiTower(num - 1, a, c, b);

            // 最下面的盘 A -> C
            System.out.println("第" + num + "个盘 " + a + "->" + c);

            // B塔所有盘  B -> C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
