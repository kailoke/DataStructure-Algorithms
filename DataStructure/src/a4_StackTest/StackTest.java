package a4_StackTest;

import java.util.Stack;

/** 栈：先进(push)后出(pop)
 * 限制线性表中元素的插入和删除只能在线性表的同一端进行
 * 栈顶：允许插入和删除的一端
 * 栈底：另一端固定为起始点
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i+1 +"号");
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
