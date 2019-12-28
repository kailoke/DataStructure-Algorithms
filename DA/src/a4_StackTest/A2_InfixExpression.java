package a4_StackTest;

/*  InfixExpression
  栈 : 计算中缀表达式 字符串：7*2*2-5+1-5+3-4
    1.数栈：存放扫描、或计算的数字
    2.符号栈：  入栈符号的运算优先级<=栈顶符号，则执行栈顶运算符
        > 符号栈为空，则直接入栈
        > 符号栈非空，则进行比较
            > 若当前操作符的优先级＜= 栈顶操作符，则pop数栈两数据和符号栈栈顶符号进行运算
                > 运算后的数据入数栈，当前操作符入符号栈。高运算优先级的始终会被执行，当前操作符不用循环比较
            > 若当前操作符的优先级 > 栈顶操作符，则当前操作符直接入栈
    3.扫描完毕后，依次pop数栈中的数和符号栈中的符号进行运算，最后数栈存留一个表达式结果
    4.出栈运算： 后出栈数[操作符]先出栈数
 */
public class A2_InfixExpression {
    // 判断是否为运算符
    static boolean isOpt(int val){
        return  (val=='+' || val=='-' || val=='*' || val=='/' || val=='(' || val==')');
    }
    // 判断运算符的优先级，优先级越高数字越大
    static int OperatorPrecedence(int opt){
        if (opt == '*' || opt == '/'){
            return 1;
        } else if ( opt == '+' || opt == '-'){
            return 0;
        }
        throw new IllegalArgumentException("传入的运算符类型不正确！");
    }
    // 计算方法
    private static int calculate(int num1, int num2, int opt){
        switch (opt){
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                throw new IllegalArgumentException("传入的运算符类型不正确！");
        }
    }
    public static void main(String[] args) {
        String exp = "70+2*6-40";     // "7+2*6-4"-->"70+2*6-4"-->"70+2*6-40"
        A1_MyStack stackNum = new A1_MyStack(10);   // 数字栈
        A1_MyStack stackOpt = new A1_MyStack(10);   // 符号栈

        int temp;                                           // 读取字符缓存
        StringBuilder buffer = new StringBuilder();         // 缓存数字
        for (int i = 0; i < exp.length(); i++) {
            temp = exp.charAt(i);
            if (!isOpt(temp)){
                // 数字(需要考虑多位数字)
                buffer.append(temp - '0');
                if ( i < exp.length() -1){  // 不是最后一位数字
                    if (isOpt(exp.charAt(i + 1))) { // 查看其后继字符若是运算符，则push数字入栈
                        stackNum.push(Integer.parseInt(buffer.toString()));
                        buffer.delete(0,buffer.length());
                    }
                    // 其后继不是运算符则不作任何处理，循环append to StringBuilder
                }else {  // 是最后一位数字，则直接push数字入栈
                    stackNum.push(Integer.parseInt(buffer.toString()));
                    buffer.delete(0,buffer.length());
                }
                // 操作符
            }else{
                // 符号栈为空，直接入栈
                if (stackOpt.isEmpty()){
                    stackOpt.push(temp);
                    // 符号栈不为空，且当前操作符的优先级＜= 符号栈栈顶操作符，则运算栈顶操作符
                }else if ( OperatorPrecedence(temp) <= OperatorPrecedence(stackOpt.peek())){
                    stackNum.push(calculate(stackNum.pop(),stackNum.pop(),stackOpt.pop()));
                    // 加入符号栈
                    stackOpt.push(temp);
                }else { // 当前操作符优先级>栈顶运算符
                    stackOpt.push(temp);
                }
            }
        }
        stackNum.show();
        stackOpt.show();

        // 扫描完毕后运行数栈和符号栈所有结果，直到数栈留下一个结果值
        while (!stackOpt.isEmpty()){
            stackNum.push(calculate(stackNum.pop(),stackNum.pop(),stackOpt.pop()));
        }

        System.out.println("结算结果:" + stackNum.peek());
    }
}
