package a5_Recursion;

/*  递归：Recursion
    > 递归就是方法自己调用自己，每次调用时传入不同的变量。递归必须向退出递归的条件逼近。
    > 递归一定会使用栈
    > 局部变量是独立的，不会相互影响；但方法中的引用类型变量是共享的 -> 指针传递
    > 根据栈的入栈、出栈规则。上一个出栈结果作为待出栈函数的局部变量
 */
public class A1_Recursion {
    public static void main(String[] args) {
        printRecursion(5);

        int factorial = factorial(5);
        System.out.println("阶乘问题：" + factorial);
    }

    private static void printRecursion(int n) {
        if (n > 2) {
            printRecursion(n - 1);
        }
        System.out.println("递归打印 n=" + n);
    }

    public static int factorial(int n ){
        if (n==1){
            return 1;
        }else {
            return n * factorial(n-1);
        }
    }
}
