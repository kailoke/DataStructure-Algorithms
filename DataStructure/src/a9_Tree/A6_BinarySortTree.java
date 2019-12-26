package a9_Tree;

/*
    二叉排序树:  相对于 数组 && 链表，同时拥有增删改和检索效率 crud
    > 任何一个 非叶子节点 ，要求其左子节点的值 < 它，其右子节点的值 >= 它
        > 有相同值则可以放在 左/右， 推荐放 右
    > 二叉排序树的CRUD，按值大小分叉减少了遍历次数

    排序树删除节点：
    > 1. 叶子节点，直接删除
    > 2. 非叶子节点+1个子节点，其子节点替代这个非叶子节点
    > 3. 非叶子节点+2个子节点，将其右子树的最小值 赋值 给自身，即让右子树的最小权替代自身"位置"

    * 问题：最差是成为类似 单向链表 的二叉树，因为左或右子节点为null的判断，相比链表反而增加了遍历判断次数
 */

public class A6_BinarySortTree {
    public static void main(String[] args) {
        // preOrder: 1 3 5 7 9 10 12
        int[] arr1 = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i : arr1) {
            tree.add(new Node(i));
        }
        System.out.println("BinarySortTree infixOrder : ");
        tree.infixList();

        System.out.println("removed tree: ");
        tree.remove(7);
        tree.remove(12);
        tree.remove(9);
        Node remove = tree.remove(10);
        tree.remove(5);
        tree.remove(1);
        tree.remove(2);

//        System.out.println(remove == null ? "移除失败" : "移除Node的父节点 : "+remove.getValue());
        tree.infixList();
    }
}

class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    void infixList(){
        if (root == null){
            System.out.println("...");
        }else {
            root.infixList();
        }
    }

    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    private Node search(int value){
        if (root != null) {
            return root.search(value);
        }
        return null;
    }

    // 找到 非叶子节点的右侧子节点的最小值
    private int getRightedMin(Node node){
        if (node.getLeft() != null){
            return getRightedMin(node.getLeft());
        }else {
            return node.getValue();
        }
    }

    // 删除节点： 1 叶子节点(仅根节点)    2 非叶子节点+1个其子节点(根节点)    3 非叶子几点+2个其子节点
    public Node remove(int value) {
        Node target = search(value);
        Node parent = null;
        if (target != null) {
            // 0.待删除的不是根节点则寻找 待删除节点的父节点
            if (root.getValue() != value) parent = root.searchParent(value);
            // 1. 删叶子节点
            if (target.getLeft() == null && target.getRight() == null) {
                // 1.1 删的是根节点
                if (parent == null) {
                    root = null;
                    // 1.2 删的不是根节点
                } else {
                    // 删父节点左子节点
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(null);
                        // 删父节点右子节点
                    } else {
                        parent.setRight(null);
                    }
                }
                // 3. 非叶子节点+2个其子节点
            } else if (target.getLeft() != null && target.getRight() != null) {
                int temp = getRightedMin(target.getRight());
                remove(temp);
                target.setValue(temp);

                // 2.非叶子节点+1个其子节点
            } else {
                // 2.1删根节点
                if (parent == null) {
                    root = root.getLeft() != null ? root.getLeft() : root.getRight();
                    // 2.2非根节点
                } else {
                    // 删父节点的 左子节点
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(target.getLeft() != null ? target.getLeft() : target.getRight());
                        // 删父节点的 右子节点
                    } else {
                        parent.setRight(target.getLeft() != null ? target.getLeft() : target.getRight());
                    }
                }
            }
        }
        return parent;
    }
}
