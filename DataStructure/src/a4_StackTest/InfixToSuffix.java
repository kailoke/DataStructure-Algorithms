package a4_StackTest;

import java.util.ArrayList;
import java.util.Stack;
import static a4_StackTest.InfixExpression.OperatorPrecedence;
import static a4_StackTest.InfixExpression.isOpt;
/*
    难点：循环比较 待入栈运算符 与栈顶运算符 的运算优先级
    > 即将组合( 运算符 ) => 高优先执行()，栈顶运算符 入数字栈，并去除运算符栈顶'('
    > 栈顶运算符 >= 待入栈运算符， 栈顶运算符 入 数字栈；再循环比较下一个栈顶是否需要进行运算
 */

public class InfixToSuffix {
    public static void main(String[] args) {
        // 1 + ((2 + 3) * 4) - 5
        // get:123+4*+5-
        String infix = "1+((2+3)*4)-5";
        String suffix = testITS(infix);
        System.out.println("suffix get:" + suffix);

    }
    public static String testITS (String infix) throws IllegalArgumentException{
        Stack<String> num = new Stack<>();
        Stack<String> opt = new Stack<>();
        char[] in = infix.replaceAll(" ", "").toCharArray();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.length;) {
            if (isOpt(in[i])){
                list.add(String.valueOf(in[i]));
                i++;
            }else {
                while (!isOpt(in[i])){
                    sb.append(in[i++]);
                    // 最后一位数字后，跳出
                    if (i == in.length) break;
                }
                list.add(sb.toString());
                sb.delete(0,sb.length());
            }
        }
        System.out.println("list:" + list);
        for (String c : list) {
            // 运算符
            switch (c) {
                case "+":
                case "-":
                case "*":
                case "/":
                    // 符号栈非空
                    while (!opt.isEmpty()) {
                        // 栈顶不是'('
                        if (!opt.peek().equals("(")) {
                            // 栈顶运算符需要先进行运算
                            if (OperatorPrecedence(opt.peek().charAt(0)) >= OperatorPrecedence(c.charAt(0))) {
                                num.push(opt.pop());
                                // 否则跳出，将操作符压入 符号栈
                            } else {
                                break;
                            }
                            //  栈顶是'('则跳出，将'('压入 符号栈
                        } else {
                            break;
                        }
                    }
                    opt.push(c);
                    break;
                // '('
                case "(":
                    opt.push(c);
                    break;
                // ')'，将符号栈顶压入数字栈，并消除符号栈下一个栈顶'('
                case ")":
                    num.push(opt.pop());
                    System.out.println("符号栈pop :" + opt.pop());
                    break;
                // 数字
                default:
                    num.push(c);
                    break;
            }
        }
        System.out.println("num stack1:" + num.toString());
        System.out.println("opt stack1:" + opt.toString());

        while (!opt.isEmpty()){
            num.push(opt.pop());
        }
        System.out.println("num stack2:" + num.toString());
        System.out.println("opt stack2:" + opt.toString());

        while (!num.isEmpty()){
            sb.append(num.pop());
        }
        return sb.reverse().toString();
    }
}
