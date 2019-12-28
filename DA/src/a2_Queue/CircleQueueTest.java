package a2_Queue;

import java.util.Scanner;

/* 数组模拟环形队列
    > 通过取模 % 的方式使数组索引可以在maxSize - 1 内循环
    > 队列满： (rear + 1) % maxSize == front
        > 将队列容量(rear所指)空出一个，判断是否是 rear+1 后追上 front，从而判断rear在front前(index更小)，二者重合则无法判断先后
        > 因 front 始终取模循环 (front+1) % maxSize，则 front 初始值 = 0;指向下一个需要取出的数据
        > rear 初始值 = 0，指向下一个需要插入的数据位置
    > 队列空：front == rear。即front取完全部数据后追上rear
 */
public class CircleQueueTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char order;
        boolean flag = true;
        System.out.print("请初始化CircleQueue(int): ");
        CircleQueue circleQueue = new CircleQueue(scanner.nextInt());
        while (flag){
            System.out.println("***********************************");
            System.out.println("\t 0.(showAll)：显示队列所有数据");
            System.out.println("\t 1.(show):显示队列");
            System.out.println("\t 2.(add) :添加数据");
            System.out.println("\t 3.(get) :取出数据");
            System.out.println("\t 4.(peek):查看头数据");
            System.out.println("\t 5.(exit):退出程序");
            System.out.println("***********************************");
            System.out.print("请操作:");

            order = scanner.next().charAt(0);
            switch (order){
                case '0':
                    circleQueue.showAll();
                    break;
                case '1':
                    circleQueue.show();
                    break;
                case '2':
                    System.out.print("请输入添加的数据(int)：");
                    circleQueue.add(scanner.nextInt());
                    break;
                case '3':
                    try {
                        System.out.println("get : " + circleQueue.get());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    break;
                case '4':
                    try {
                        System.out.println("peek : " + circleQueue.peek());
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                case '5':
                    flag = false;
                    break;
            }
        }
        scanner.close();
        System.out.println("程序退出");
    }
}

class CircleQueue extends MyQueue{
    public CircleQueue(){
        this(10);
    }
    public CircleQueue(int maxSize) {
        //成员int 默认=0
//        this.front = 0;
//        this.rear = 0;
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    @Override
    public boolean isFull() {
        // rear和front相邻即满，空出一个空间以判断是rear+1 == front
        return (rear + 1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public void add(int i) {
        if (isFull()){
            System.out.println("队列已满...无法加入");
            return;
        }
        arr[rear] = i;
        // rear始终在 %maxSize 模内循环
        rear = (rear + 1) % maxSize;
    }

    @Override
    public int get() throws ArrayIndexOutOfBoundsException{
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException("队列中没有数据，无法取出");
        }
        int result = this.arr[front];
        front = (front + 1) % maxSize;
        return result;
    }

    // 队列中有效的数据个数 ： (rear - front + maxSize) % maxSize
    int size(){
        // 若 rear > front : maxSize 取模自己则抵消
        // 若 rear < front : 差值负数 + maxSize == 差值外的个数
        return (rear - front + maxSize) % maxSize;
    }

    @Override
    public void show() {
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        System.out.print("剩余队列：[ ");
        for (int i = front ; i < front + this.size(); i++) {
            System.out.print(this.arr[i % maxSize] + " ");
        }
        System.out.println("]");
    }

    @Override
    public int peek() {
        if (isEmpty()){
            System.out.println("队列为空，无数据");
        }
        return this.arr[front];
    }

    public void showAll(){
        System.out.print("所有队列:");
        // 从front开始遍历，但有一个空间闲置
        for (int i = front; i < maxSize - 1; i++) {
            System.out.println(this.arr[front % maxSize]);
        }
    }
}
