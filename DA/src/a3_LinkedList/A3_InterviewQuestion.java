package a3_LinkedList;

import java.util.Stack;

public class A3_InterviewQuestion {
    // 1.获取单链表的节点个数，不统计头节点个数
    public static int getLength(HeroNode head){
        if (head.next ==null){
            return 0;
        }
        HeroNode cur = head.next;
        int length = 0;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    // 2.查找单链表的倒数第K个节点
    public static HeroNode countDown(HeroNode head,int index){
        if (head.next == null){
            return null;
        }
        int length = getLength(head);
        if (index > length){
            return null;
        }
        HeroNode cur = head.next;
        int loop = length - index;
        while ( loop-- > 0){
            cur = cur.next;
        }
        return cur;
    }

    // 3.单链表反转
    // 自写递归
    public static void recurseReverse(HeroNode head){
        innerReverse(head,head.next);
    }
    public static HeroNode innerReverse(HeroNode head,HeroNode node){
        if (node.next == null){
            // 头指向最后一个节点
            head.next = node;
            return node;
        }
        // temp 逆序出栈节点
        HeroNode temp = innerReverse(head,node.next);
        // node 为当前函数节点 temp为出栈函数节点
        temp.next = node;
        node.next = null;
        return node;
    }
    // 头插法
    public static HeroNode whileReverse(HeroNode head){
        if (head.next == null || head.next.next == null){
            return head;
        }
        // 当前指针
        HeroNode cur = head.next;
        // 下一个指针
        HeroNode next;
        // 断头
        head.next = null;
        // 开始头插
        while (cur != null){
            // 缓存下一个指针
            next = cur.next;
            // 获取头部后继
            cur.next = head.next;
            // 头插当前节点
            head.next = cur;
            // 指定下一个待插入节点
            cur = next;
        }
        return head;
    }

    // 4.从尾到头打印单链表
    // 递归实现
    public static HeroNode printReverse(HeroNode head){
        if (head.next == null){
            return head;
        }
        // temp 逆序出栈节点
        HeroNode temp = printReverse(head.next);
        System.out.println("逆序递归打印链表: " + temp);
        return head;
    }
    // 栈特性实现
    public static void stackReverse(HeroNode head) {
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            System.out.println("逆序栈特性打印链表: " + stack.pop());
        }
    }
}
