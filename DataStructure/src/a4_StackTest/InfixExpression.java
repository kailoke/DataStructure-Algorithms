package a4_StackTest;

/*
    使用栈 模拟计算器计算 字符串：7*2*2-5+1-5+3-4 的结果
    1.数栈：存放扫描、或计算的数字
    2.符号栈：
        > 符号栈为空，则直接入栈
        > 符号栈非空，则进行比较
            > 若当前操作符的优先级＜= 栈顶操作符，则pop数栈两数据和符号栈1符号，运算后的数据入数栈，当前操作符入符号栈
            > 若当前操作符的优先级 > 栈顶操作符，则当前操作符直接入栈
    3.扫描完毕后，依次pop数栈中的数和符号栈中的符号进行运算，最后数栈存留一个表达式结果

    !!中缀表达式!! 因为运算符优先级的问题，需要使用两个栈
 */
public class InfixExpression {
    // 判断是否为运算符
    private static boolean isOpt(int val){
        return  (val == '+' || val == '-' || val == '*' || val == '/');
    }
    // 判断运算符的优先级，优先级越高数字越大
    private static int OperatorPrecedence(int opt){
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
        MyStack stackNum = new MyStack(10);
        MyStack stackOpt = new MyStack(10);
        int temp;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            temp = exp.charAt(i);
            if (!isOpt(temp)){
                // 数字(需要考虑多位数字)
                buffer.append(temp - '0');
                if ( i < exp.length() -1){
                    if (isOpt(exp.charAt(i + 1))) {
                        stackNum.push(Integer.parseInt(buffer.toString()));
                        buffer.delete(0,buffer.length());
                    }
                }else {
                    stackNum.push(Integer.parseInt(buffer.toString()));
                    buffer.delete(0,buffer.length());
                }

            }else{  // 操作符
                // 符号栈为空
                if (stackOpt.isEmpty()){
                    stackOpt.push(temp);
                    // 符号栈不为空，且当前操作符的优先级＜= 符号栈栈顶操作符，则按从左至右原则应进行左侧运算
                }else if ( OperatorPrecedence(temp) <= OperatorPrecedence(stackOpt.peek())){
                    stackNum.push(calculate(stackNum.pop(),stackNum.pop(),stackOpt.pop()));
                    stackOpt.push(temp);
                    // 加入符号栈
                }else {
                    stackOpt.push(temp);
                }
            }
        }
        stackNum.show();
        stackOpt.show();

        while (!stackOpt.isEmpty()){
            stackNum.push(calculate(stackNum.pop(),stackNum.pop(),stackOpt.pop()));
        }

        System.out.println("结算结果:" + stackNum.peek());
        stackNum.show();
        stackOpt.show();
    }
}
