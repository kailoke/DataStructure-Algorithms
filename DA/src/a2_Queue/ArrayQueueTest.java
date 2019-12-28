package a2_Queue;
import java.util.Scanner;

/*
    队列：先进先出
    数组模拟队列:
      > maxSize 队列最大容量
      > front   头指针 默认=-1,指向已经取出的最后一个元素，-1表示还没开始取出
      > rear    尾指针 默认=-1,指向已经添加的最后一个元素，-1表示还没开始取出

      * 数组队列，数组栈，首要考虑 空 和 满的条件
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char order;
        boolean flag = true;
        System.out.print("请初始化ArrayQueue(int): ");
        ArrayQueue arrayQueue = new ArrayQueue(scanner.nextInt());
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
                    arrayQueue.show();
                    break;
                case 'a':
                    System.out.print("请输入添加的数据(int)：");
                    arrayQueue.add(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.println("get : " + arrayQueue.get());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'p':
                    try {
                        System.out.println("peek : " + arrayQueue.peek());
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

class ArrayQueue extends MyQueue{

    public ArrayQueue(){
        this(10);
    }

    ArrayQueue(int maxSize){
        this.front = -1;
        this.rear = -1;
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    public boolean isFull(){
        // maxSize = rear + 1
        return this.rear == this.maxSize - 1;
    }

    public boolean isEmpty(){
        return this.front == this.rear;
    }

    public void add(int n){
        if (isFull()){
            System.out.println("队列已满...无法加入");
            return;
        }
        this.arr[++rear] = n;
    }

    public int get() throws ArrayIndexOutOfBoundsException{
        if (isEmpty()){
            return this.arr[++front];
        }
        throw new ArrayIndexOutOfBoundsException("队列中没有数据，无法取出");
    }

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

    public int peek() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return this.arr[front + 1];
    }
}
