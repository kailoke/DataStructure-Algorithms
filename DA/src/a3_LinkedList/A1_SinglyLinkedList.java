package a3_LinkedList;


/**
    使用 单向链表 管理武将列表
    > 末尾添加
    > 节点排序：加入时遍历，找到插入位置
 */
public class A1_SinglyLinkedList {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(new HeroNode.Info(1,"宋江","及时雨"));
        HeroNode hero2 = new HeroNode(new HeroNode.Info(2,"卢俊义","玉麒麟"));
        HeroNode hero3 = new HeroNode(new HeroNode.Info(3,"吴用","智多星"));
        HeroNode hero4 = new HeroNode(new HeroNode.Info(4,"林冲","豹子头"));

        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.add(hero3);
        list1.add(hero2);
        list1.add(hero4);
        list1.add(hero1);
        list1.show();

        // add
        SinglySortedLinkedList list2 = new SinglySortedLinkedList();
        list2.add(hero3);
        list2.add(hero2);
        list2.add(hero4);
        list2.add(hero1);
        list2.show();
        list2.add(hero2);

        // update
        HeroNode hero5 = new HeroNode(new HeroNode.Info(3,"替代吴用","智多星+1"));
        list2.update(hero5);
        list2.show();

        // delete
//        list2.delete(1);
        list2.show();

        // Interview getLength
        System.out.println("Interview getLength:" + A3_InterviewQuestion.getLength(list2.getHead()));

        // Interview countDown
        System.out.println("Interview countDown:" + A3_InterviewQuestion.countDown(list2.getHead(),2));

        // Interview reverse
        A3_InterviewQuestion.recurseReverse(list2.getHead());
        System.out.println("recurse reserve:");list2.show();
        A3_InterviewQuestion.whileReverse(list2.getHead());
        System.out.println("whileReverse:");list2.show();

        // Interview printReverse
        A3_InterviewQuestion.printReverse(list2.getHead());
        A3_InterviewQuestion.stackReverse(list2.getHead());
    }
}

/*
    Node
 */
class HeroNode{
    Info info;
    HeroNode next;

    public HeroNode() {
    }

    public HeroNode(Info info) {
        this.info = info;
    }

    static class Info{
        int no;
        String name;
        String alias;

        public Info() {
        }

        public Info(int no, String name, String alias) {
            this.no = no;
            this.name = name;
            this.alias = alias;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", alias='" + alias + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "info=" + info +
                '}';
    }
}
/*
    管理node，末尾添加
 */
class SinglyLinkedList {
    private final HeroNode head = new HeroNode(new HeroNode.Info());
    private HeroNode last = null;

    public HeroNode getHead() {
        return head;
    }

    // 末尾添加，使用last标记尾结点，不使用遍历
    public void add(HeroNode node){
        if (head.next == null){
            last = head;
        }
        last.next = node;
        last = node;
    }

    // 显示链表
    public void show(){
        if (head.next == null){
            return;
        }
        HeroNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("*****************************");
    }
}

/*
    自然排序
 */
class SinglySortedLinkedList {
    private final HeroNode head = new HeroNode(new HeroNode.Info());

    public HeroNode getHead() {
        return head;
    }

    // 添加节点
    public void add(HeroNode node){
        HeroNode temp = head;
        boolean flag = false;   // 已存在标记
        // 单向链表，temp.next 的值进行判断，可操作其 直接前驱节点 || 后继节点
        while (temp.next != null){
            // node已存在
            if (temp.next.info.no == node.info.no){
                flag = true;
                System.out.printf("武将 %d 已存在!添加失败\n", node.info.no);
                break;
            }
            // 找到插入位置
            if (temp.next.info.no > node.info.no){
                break;
            }
            temp = temp.next;
        }
        // 不存在相同值，则插入新节点到指定位置
        if (!flag){
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 显示链表
    public void show(){
        if (head.next == null){
            return;
        }
        HeroNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("*****************************");
    }

    // update
    public void update(HeroNode node){
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null){
            if (temp.next.info.no == node.info.no){
                node.next = temp.next.next;
                temp.next = node;
                System.out.printf("%d 修改成功!\n", node.info.no);
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            System.out.println("目标武将不存在，已自动添加");
            add(node);
        }
    }

    // delete
    public void delete(int index){
        HeroNode temp = head;
        while (temp.next != null){
            if (temp.next.info.no == index){
                temp.next = temp.next.next;
                System.out.printf("%d 删除成功!\n", index);
                break;
            }
            temp = temp.next;
        }
    }
}
