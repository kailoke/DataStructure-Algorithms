package a8_Tree;

/*
    数组：通过下表访问元素非常快，增删改操作效率低，检索目标效率低
    链表：增删操作效率高，但检索目标效率低(改需要先检索)
    树：提高检索速度、也同时保证数据的插入、删除、修改的速度

    二叉树：
    > 1. 比较次数，仅当比较节点的权和查找值时，才是比较次数，其余均为left\right探路
 */
public class A1_BinaryTree {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 增加节点
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 手动创建二叉树，之后使用递归的方式创建二叉树
        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.infixOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();

        System.out.println("前序查找：---------------------------------");
        HeroNode resp = binaryTree.preSearch(5);
        if (resp != null){
            System.out.println("前序查找到信息为:" + resp);
        }else {
            System.out.println("前序查找未找到英雄信息");
        }

        System.out.println("中序查找：---------------------------------");
        resp = binaryTree.infixSearch(5);
        if (resp != null){
            System.out.println("中序查找到信息为:" + resp);
        }else {
            System.out.println("中序查找未找到英雄信息");
        }

        System.out.println("后序查找：---------------------------------");
        resp = binaryTree.postSearch(2);
        if (resp != null){
            System.out.println("后序查找到信息为:" + resp);
        }else {
            System.out.println("后序查找未找到英雄信息");
        }

        System.out.println("删除节点测试-------------------------------");
        System.out.println("删除前：");
        binaryTree.preOrder();
        int delete = binaryTree.delete(6);
        if (delete > 0 ){
            System.out.print("删除成功，");
        }else {
            System.out.print("删除失败!");
        }
        System.out.println("删除后：");
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 以下为三种遍历方法
    public void preOrder() {
        if (this.root != null){
            this.root.preList();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixList();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    public void postOrder(){
        if (this.root != null) {
            root.postList();
        }else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    // 以下为三种查找方法
    public HeroNode preSearch(int id){
        if (this.root != null){
            return root.preSearch(id);
        }else {
            return null;
        }
    }
    public HeroNode infixSearch(int id){
        if (this.root != null){
            return root.infixSearch(id);
        }else {
            return null;
        }
    }
    public HeroNode postSearch(int id){
        if (this.root != null){
            return root.postSearch(id);
        }else {
            return null;
        }
    }

    // 删除
    public int delete(int id){
        if (root != null){
            if (root.getId() != id){
                return root.deleteNode(id);
            }else {
                root = null;
                return 1;
            }
        }else {
            System.out.println("树空的哈");
            return -1;
        }
    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    void preList(){
        // 先输出本节点
        System.out.println(this);
        // 向左遍历
        if (this.left != null){
            this.left.preList();
        }
        // 左侧均遍历结束后向右遍历
        if (this.right != null){
            this.right.preList();
        }
    }
    // 中序遍历
    void infixList() {
        if (left != null) {
            left.infixList();
        }
        System.out.println(this);
        if (right != null) {
            right.infixList();
        }
    }
    // 后序遍历
    void postList(){
        if (left != null){
            left.postList();
        }
        if (right != null){
            right.postList();
        }
        System.out.println(this);
    }

    // 前序查找
    HeroNode preSearch(int id){
        System.out.println("前序查找中");
        if (this.id == id){
            return this;
        }
        HeroNode temp = null;
        if (this.left != null){
            temp = this.left.preSearch(id);
            if (temp != null){
                return  temp;
            }
        }
        if (this.right != null){
            temp = this.right.preSearch(id);
        }
        return temp;
    }
    // 中序查找
    HeroNode infixSearch(int id) {
        HeroNode temp = null;
        if (left != null) {
            temp = left.infixSearch(id);
            if (temp != null) return temp;
        }
        System.out.println("中序查找中");
        if (this.id == id){
            return this;
        }
        if (right != null) {
            temp = right.infixSearch(id);
        }
        return temp;
    }
    // 后序查找
    HeroNode postSearch(int id){
        HeroNode temp;
        if (left != null){
            temp = left.postSearch(id);
            if (temp != null){
                return temp;
            }
        }
        if (right != null){
            temp = right.postSearch(id);
            if (temp != null){
                return temp;
            }
        }
        System.out.println("后序查找中");
        if (this.id == id){
            return this;
        }
        return null;
    }

    // 删除节点
    // 1. 如果是叶子节点，则删除该节点
    // 2. 如果是非叶子节点，则删除子树
    int deleteNode(int id){
        int resp = -1;
        // 因为是单向链表，所以判断当前节点的下一节点是否满足条件
        if (this.left != null && this.left.id == id){
            this.left = null;
            resp = 1;
            return resp;
        }
        if (this.right != null && this.right.id == id){
            this.right = null;
            resp = 1;
            return resp;
        }

        // 左右递归，减少对叶子节点的遍历次数；减少对子节点的遍历次数
        if (this.left != null){
            if (this.left.left != null || this.left.right !=null)
                resp = this.left.deleteNode(id);
        }
        if (this.right != null){
            if (this.right.left != null || this.right.right !=null)
                resp = this.right.deleteNode(id);
        }
        return resp;
    }
}
