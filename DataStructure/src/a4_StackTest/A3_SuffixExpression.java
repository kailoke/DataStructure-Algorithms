package a4_StackTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
  一、逆波兰表达式：后缀表达式，根据中缀表达式转换而成的可直接入栈运算的表达式，
        从左至右扫描，若是数字则入栈，若是符号由pop2 运算 pop1 的结果入栈

  二、前缀表达式：？
        从右至左扫描，数字先全入栈，若是符号则弹出栈顶 pop2 运算 pop1 的结果入栈
 */
public class A3_SuffixExpression {
    public static void main(String[] args) {
        // "(30+4)*5-6" => "30 4 + 5 * 6 -"
        String suffixExp = "30 4 + 5 * 6 -";
        // 1.过滤数据
        String[] filteredS = suffixExp.split(" ");
        // Arrays.asList(String[] str) 是Arrays内部静态类，直接引用数组，没有重写add()\remove(); 不能直接修改
        ArrayList<String> list = new ArrayList(Arrays.asList(filteredS));
        // 2.栈进行计算
        Stack<Integer> stack = new Stack<>();
        int num1;   // 缓存数1
        int num2;   // 缓存数2
        for (String s : list) {
            switch (s){
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "-":
                    stack.push(-(stack.pop()-stack.pop()));
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "/":   // 后出栈/先出栈
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2/num1);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        System.out.println(stack.peek());
    }
}
