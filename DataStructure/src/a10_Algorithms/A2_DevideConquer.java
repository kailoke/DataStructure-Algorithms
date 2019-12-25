package a10_Algorithms;

/*

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
