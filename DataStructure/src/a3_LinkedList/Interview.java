package a3_LinkedList;

public class Interview {
    // 1.获取单链表的节点个数，不统计头节点个数
    public static int getLength(HeroNode head){
        if (head.next ==null){
            return 0;
        }
        HeroNode cur = head.next;
        int length =
                0;
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
    // 递归实现  此处如何合并成一个方法？
    public static void recurseReverse(HeroNode head){
        innerReverse(head,head.next);
    }
    public static HeroNode innerReverse(HeroNode head,HeroNode node){
        if (node.next == null){
            head.next = node;
            return node;
        }
        HeroNode temp = innerReverse(head,node.next);
        temp.next = node;
        node.next = null;
        return node;
    }
    // 辅助变量实现
    public static HeroNode reverse2(HeroNode head){
        HeroNode cur = head.next;
        HeroNode next = null;
        head.next = null;
        while (cur != null){
            next = cur.next;
            cur.next = head.next;
            head.next = cur;
            cur = next;
        }
        return head;
    }

    // 4.从尾到头打印单链表
    public static HeroNode printReverse(HeroNode head){
        if (head.next == null){
            return head;
        }
        HeroNode temp = printReverse(head.next);
        System.out.println("reverse print: " + temp);
        return head;
    }
}
