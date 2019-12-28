package a4_StackTest;

import java.util.ArrayList;
import java.util.Stack;
import static a4_StackTest.A2_InfixExpression.OperatorPrecedence;
import static a4_StackTest.A2_InfixExpression.isOpt;

/*中缀表达式 转换为 后缀表达式 ： 将需要执行的运算符入后缀表达式
    难点：循环比较 待入栈运算符 与栈顶运算符 的运算优先级
    > 即将组合( 运算符... ) => 需立即执行(运算符..)，括号内运算符 入数字栈，并去除运算符栈顶'('
    > 栈顶运算符 >= 待入栈运算符， 栈顶运算符入 数字栈；再循环比较下一个栈顶是否需要进行运算

    * 结果栈pop后逆序 即是后缀表达
    * 结果栈的逆序已经是后缀表达式，传统做法pop就会逆序结果表达式，所以需要再一次逆序
 */

public class A4_InfixToSuffix {
    public static void main(String[] args) {
        // 1 + ((2 + 3) * 4) - 5
        // get:123+4*+5-
        String infix = "1+((2+3)*4)-5";
        String suffix = testITS(infix);
        System.out.println("suffix get:" + suffix);

    }
    public static String testITS (String infixExp) throws IllegalArgumentException{
        Stack<String> numStack = new Stack<>();
        Stack<String> opt = new Stack<>();
        // \\s+ 匹配任何空白字符
        // 以下为对infix数组进行清洗，生成infix数组
        char[] infix = infixExp.replaceAll("\\s+", "").toCharArray();
        ArrayList<String> infixList = new ArrayList<>();  // infix扫描结果数组
        StringBuilder num = new StringBuilder();          // 多位数缓存
        for (int i = 0; i < infix.length;) {
            if (isOpt(infix[i])){   // 运算符
                infixList.add(String.valueOf(infix[i]));
                i++;
            }else {
                // 数字，多位数
                while (!isOpt(infix[i])){
                    num.append(infix[i++]);
                    // 最后一位数字后，跳出
                    if (i == infix.length) break;
                }
                infixList.add(num.toString());
                num.delete(0,num.length());
            }
        }
        System.out.println("infix扫描数组:" + infixList);
        // 中缀转后缀
        for (String c : infixList) {
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
                                numStack.push(opt.pop());
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
                case ")":
                    // pop 括号内的所有运算符
                    while (!opt.peek().equals("(")){
                        numStack.push(opt.pop());
                    }
                    // pop "("
                    System.out.println("符号栈pop:" + opt.pop());
                    break;
                // 数字
                default:
                    numStack.push(c);
                    break;
            }
        }
        System.out.println("numStack stack1:" + numStack.toString());
        System.out.println("opt stack1:" + opt.toString());

        while (!opt.isEmpty()){
            numStack.push(opt.pop());
        }
        System.out.println("numStack stack2:" + numStack.toString());
        System.out.println("opt stack2:" + opt.toString());

        // 此处可简化，不入结果栈，直接入StringBuilder即是顺序的后缀表达式
        while (!numStack.isEmpty()){
            num.append(numStack.pop());
        }
        return num.reverse().toString();
    }
}
