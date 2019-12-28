package a4_StackTest;

/*
    栈 : 数组 模拟
    可穷尽数组，在遍历/索引开始前，习惯上都用-1(数组开始前) <===> 参考Iterator
 */
public class A1_MyStack {
    private int top = -1;   // 栈顶
    private int maxSize;
    private int[] stack;

    public A1_MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
    // 入栈
    public void push(int i){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        stack[++top] = i;
    }

    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new NullPointerException();
        }
        return stack[top--];
    }

    // iter: 从栈顶开始
    public void show(){
        if (isEmpty()){
            System.out.println("没有数据哦");
            return;
        }
        for (int i = top; i > -1; i--){
            System.out.println("stack" + (i+1) + ":" + stack[i]);
        }
    }

    public int peek(){
        if (top == -1){
            throw new IndexOutOfBoundsException("没有数据");
        }
        return stack[top];
    }

    public static void main(String[] args) {
        A1_MyStack stack = new A1_MyStack(5);
        for (int i = 0; i < stack.maxSize; i++) {
            stack.push((i+1)*10);
        }
        stack.show();
        stack.pop();
        stack.show();
        while (stack.top > -1){
            System.out.println(stack.pop());
        }
        stack.show();
    }
}
