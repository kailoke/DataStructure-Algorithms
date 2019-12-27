package a9_Tree;

import java.util.ArrayList;

/*  HuffmanTree
    赫夫曼树： 带权路径长度(WPL:wighted Path Length)最小的树，权值较大的节点离根较近
              给定n个权值作为n个叶子节点，构造的最优二叉树
    > 路径 ： 从A节点到B节点经过的通路
    > 路径长度 ： 从根节点到目标节点经过的分支数目。
    > 节点的权 ： 节点赋值
    > 带权路径长度 ： 路径长度 * 节点的权
    > 树的带权路径长度 WPL(Weight Path Length) : 所有叶子节点的带权路径长度和

  构建赫夫曼树
    > 1. 将权值封装到Node
    > 2. 进行排序，取出根节点权值最小的两颗二叉树作为"子节点"，从NodeList中移除，
                  他们的权值之和作为"父节点"组成一颗新二叉树
    > 3. 将新的二叉树再与其他树进行组合直到 仅剩一棵树，即为赫夫曼树
 */
public class A4_HuffmanTree {
    public static void main(String[] args) {
        int[] arr1 = {13,7,8,3,29,6,1};
        Node head = huffmanTree(arr1);

        head.PreList();
    }

    public static Node huffmanTree(int[] arr){
        // 1.将权值封装成Node,可将每个Node都看一颗二叉树
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        // 2.排序后，组合赫夫曼树
        while (nodes.size() > 1) {
            nodes.sort(((o1, o2) -> o1.getValue() - o2.getValue()));
            System.out.println(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node mid = new Node(left.getValue() + right.getValue());
            nodes.add(mid);
            mid.setLeft(left);
            mid.setRight(right);

            nodes.remove(0);
            nodes.remove(0);
        }
        // 3.返回赫夫曼树的根节点
        return nodes.get(0);
    }
}

class Node{
    private Node left;
    private Node right;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // A6_BinarySortedTree 递归添加
    public void add(Node node){
        // 添加节点值<调用节点值
        if (node.value < this.value){
            // 调用节点左子树无节点
            if ( this.left == null){
                this.left = node;
            }else {
                // 调用节点左子树有节点，则递归左子节点添加
                this.left.add(node);
            }
        // 添加节点值>=调用节点值
        }else {
            // 调用节点右子树无节点
            if ( this.right == null){
                this.right = node;
            }else {
                // 调用节点右子树右节点，则递归右子节点添加
                this.right.add(node);
            }
        }
    }

    void PreList(){
        System.out.println(this);
        if (this.left != null){
            left.PreList();
        }
        if (this.right != null){
            this.right.PreList();
        }
    }
    void infixList(){
        if (this.left != null){
            this.left.infixList();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    Node search(int value) {
        // 和前/中/后续遍历不同的是，折半搜索后无结果则可以退出递归
        if ( value < this.value){
            if (this.left != null){
                return this.left.search(value);
            }
        }else if (this.value == value) {
            return this;
        }else if (this.right != null){
            return this.right.search(value);
        }
        return null;
    }

    Node searchParent(int value) {
        if ( value < this.value){
            if (this.left != null && this.left.value == value){
                return this;
            }else if (this.left != null){
                return this.left.searchParent(value);
            }
        }else if (this.right != null && this.right.value == value){
            return this;
        }else if (this.right != null){
            return this.right.searchParent(value);
        }
        return null;
    }

    // A7_AVL
    // 返回以该节点为根节点的树的高度
    // 对于当前节点而言，其所有的出栈子节点高度+1，选择较大的高度作为当前节点高度出栈
    int height(){
        return Math.max(this.left == null ? 0 : this.left.height(),
                this.right == null ? 0 : this.right.height()) + 1;
    }

    // 获得根节点左子树高度
    int leftHeight(){
        if (this.left == null){
            return 0;
        }else {
            return this.left.height();
        }
    }

    // 获得根节点右子树高度
    int rightHeight(){
        if (this.right == null){
            return 0;
        }else {
            return this.right.height();
        }
    }
}