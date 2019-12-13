package a4_StackTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
    逆波兰表达式：后缀表达式，根据中缀表达式运算顺序转换而成
        使用栈，从左至右扫描，若是数字则入栈，若是符号由pop2 运算 pop1 的结果入栈

    前缀表达式：？
        使用栈，从右至左扫描，数字先全入栈，若是符号则弹出栈顶 pop2 运算 pop1 的结果入栈
 */
public class SuffixExpression {
    public static void main(String[] args) {
        // "(30+4)*5-6" => "30 4 + 5 * 6 -"
        String suffixExp = "30 4 + 5 * 6 -";
        // 1.过滤数据
        String[] filteredS = suffixExp.split(" ");
        ArrayList<String> list = new ArrayList(Arrays.asList(filteredS));
        // 2.配合栈进行计算
        Stack<Integer> stack = new Stack<>();
        int num1;
        int num2;
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
                case "/":
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
