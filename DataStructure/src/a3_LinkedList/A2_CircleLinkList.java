package a3_LinkedList;

/*
    单向环形链表，约瑟夫问题出队序列
 */
public class A2_CircleLinkList {
    public static void main(String[] args) {
        CircleLinkList list = new CircleLinkList(15);
//        list.show();
        list.pop(5,10);
    }
}

class Node{
    private int num;
    Node next;

    public Node(int num, Node next) {
        this.num = num;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}

class CircleLinkList{
    private Node first;

    // 构建单向环形链表
    public CircleLinkList(int size) {
        if (size<1) size = 1;

        // 头节点自循环
        first = new Node(1,null);
        first.next = first;

        // 头节点后开始循环
        Node p = first;
        for (int i = 1; i < size; i++) {
            p.next = new Node(i+1,null);
            p = p.next;
            p.next = first;
        }
    }

    public void show(){
        if (first == null){
            System.out.println("没有任何节点");
            return;
        }
        Node cur = first;
        do {
            System.out.println(cur);
            cur = cur.next;
        } while (cur != first);
    }

    // K : 从第K个开始数数
    // M : 数几下后出列，出列后下一个从1开始数
    public void pop(int k,int m){
        // 1.定位起始点
        for (int i = 1; i < k; i++) {
            first = first.next;
        }
        // 2.定位起始点pre
        Node pre = first;
        while (pre.next != first){
            pre = pre.next;
        }
        // 3.出列
        while (pre != first){       // 不是最后一个节点
            // 3.1 报数
            for (int i = 1; i < m; i++) {
                pre = pre.next;
                first = first.next;
            }
            System.out.println("出列：" + first);
            // 3.2 更新环形链表
            pre.next = first.next;
            // 3.3 更新起始点
            first = first.next;
        }
        // 最后一个出列
        System.out.println("出列: " + first);
    }
}
