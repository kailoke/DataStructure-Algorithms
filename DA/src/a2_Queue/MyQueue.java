package a2_Queue;

abstract class MyQueue {
    int[] arr;
    int maxSize;
    int front;
    int rear;

    // 判断队列是否满
    public abstract boolean isFull();
    // 判断队列是否为空
    public abstract boolean isEmpty();
    // 向队列添加数据
    public abstract void add(int i);
    // 获取队列数据
    public abstract int get();
    // show
    public abstract void show();
    // 显示头数据
    public abstract int peek();
}
