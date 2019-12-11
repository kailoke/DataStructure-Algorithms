package a2_Queue;
import java.util.Scanner;

/*  有序表
    队列：先进先出
       attribute:
        maxSize 队列最大容量
        front   头指针 默认=-1,指向队列起始之前 -->iterator
                      TODO那C这指针是啥...
        rear    尾指针 默认=-1,指向队列起始之前 -->iterator
 */
public class ArrayQueue {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char order;
        boolean flag = true;
        System.out.print("请输入初始化Queue大小(int): ");
        Queue queue = new Queue(scanner.nextInt());
        while (flag){
            System.out.println("***********************************");
            System.out.println("\t s(show):显示队列");
            System.out.println("\t a(add) :添加数据");
            System.out.println("\t g(get) :取出数据");
            System.out.println("\t p(peek):查看头数据");
            System.out.println("\t e(exit):退出程序");
            System.out.println("***********************************");
            System.out.print("请操作:");

            order = scanner.next().charAt(0);
            switch (order){
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.print("请输入添加的数据(int)：");
                    queue.add(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.println("get : " + queue.get());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'p':
                    try {
                        System.out.println("peek : " + queue.peek());
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    flag = false;
                    break;
            }
        }
        scanner.close();
        System.out.println("程序退出");
    }
}

class Queue{
    private int maxSize;
    private int front = -1;
    private int rear = -1;
    private int[] arr;

    public Queue(){
        this(10);
    }
    public Queue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }
    // 判断队列是否满
    public boolean isFull(){
        // maxSize = rear + 1
        return this.rear == this.maxSize-1;
    }
    // 判断队列是否为空
    public boolean isEmpty(){
        return this.front == this.rear;
    }
    // 向队列添加数据
    public void add(int n){
        if (!isFull()){
            this.arr[++rear] = n;
            return;
        }
        System.out.println("队列已满...无法加入");
    }
    // 获取队列数据
    public int get() throws ArrayIndexOutOfBoundsException{
        if (!isEmpty()){
            return this.arr[++front];
        }
        throw new ArrayIndexOutOfBoundsException("队列中没有数据，无法取出");
    }
    // show
    public void show(){
        if (this.front!= -1){
            System.out.print("已取出数据: [ ");
            for (int i = 0; i <= front; i++) {
                System.out.print(this.arr[i]);
            }
            System.out.println(" ]");
        }
        if (!isEmpty()){
            System.out.print("当前队列：[ ");
            for (int i = front + 1; i <= rear; i++) {
                System.out.print(this.arr[i] + " ");
            }
            System.out.println("]");
            return;
        }
        System.out.println("队列为空");
    }
    // 显示头数据
    public int peek() throws RuntimeException{
        if (!isEmpty()){
            return this.arr[front + 1];
        }
        throw new RuntimeException("队列为空");
    }
}
